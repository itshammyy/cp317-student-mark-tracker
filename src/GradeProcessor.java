import java.io.*;
import java.util.*;

/**
 * Processes student and course data from input files,
 * calculates final grades, and writes the sorted results to an output file.
 */
public class GradeProcessor {

    private static final String NAME_FILE = "NameFile.txt";
    private static final String COURSE_FILE = "CourseFile.txt";
    private static final String OUTPUT_FILE = "OutputFile.txt";

    public static void main(String[] args) {
        Map<String, String> nameMap = readNameFile(NAME_FILE);
        if (nameMap == null) {
            System.err.println("Failed to read NameFile. Exiting.");
            return;
        }

        List<Enrollment> enrollments = readCourseFile(COURSE_FILE, nameMap);
        if (enrollments == null) {
            System.err.println("Failed to read CourseFile. Exiting.");
            return;
        }

        Collections.sort(enrollments);
        writeOutputFile(OUTPUT_FILE, enrollments);

        System.out.println("Processing complete. Output written to " + OUTPUT_FILE);
    }

    /**
     * Reads student ID and name pairs from NameFile.txt.
     * @return Map of student IDs to names, or null if an error occurs.
     */
    private static Map<String, String> readNameFile(String path) {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.printf("Error: Line %d in NameFile: %s%n", lineNum, line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                if (!id.matches("\\d{9}")) {
                    System.err.printf("Error: Invalid ID format at line %d: %s%n", lineNum, id);
                    continue;
                }

                map.put(id, name);
            }
        } catch (IOException e) {
            System.err.println("Error reading NameFile: " + e.getMessage());
            return null;
        }
        return map;
    }

    /**
     * Reads course and grade data from CourseFile.txt and creates Enrollment objects.
     * @return List of valid Enrollment objects, or null if an error occurs.
     */
    private static List<Enrollment> readCourseFile(String path, Map<String, String> names) {
        List<Enrollment> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 6) {
                    System.err.printf("Error: Line %d in CourseFile: %s%n", lineNum, line);
                    continue;
                }

                String id = parts[0].trim();
                String course = parts[1].trim();

                try {
                    double t1 = Double.parseDouble(parts[2].trim());
                    double t2 = Double.parseDouble(parts[3].trim());
                    double t3 = Double.parseDouble(parts[4].trim());
                    double fin = Double.parseDouble(parts[5].trim());

                    if (!names.containsKey(id)) {
                        System.err.printf("Error: Student ID %s not found in NameFile at line %d. Skipped.%n", id, lineNum);
                        continue;
                    }

                    list.add(new Enrollment(id, names.get(id), course, t1, t2, t3, fin));
                } catch (NumberFormatException e) {
                    System.err.printf("Error: Grade parsing error at line %d: %s%n", lineNum, line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CourseFile: " + e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * Writes sorted Enrollment records to OutputFile.txt.
     */
    private static void writeOutputFile(String path, List<Enrollment> enrollments) {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println("Student ID,Student Name,Course Code,Final grade (test 1,2,3-3x20%, final exam 40%)");
            for (Enrollment e : enrollments) {
                pw.println(e);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Could not write to output file: " + e.getMessage());
        }
    }
}
