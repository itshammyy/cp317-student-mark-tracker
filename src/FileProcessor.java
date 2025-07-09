/*
 * FileProcessor.java
 * This class handles reading student names and course data from files.
 * Uses offencive programming techniques to handle errors gracefully and before they effect the rest of the program.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    public static Map<String, String> readStudentNames(String path) {
        Map<String, String> studentMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    studentMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading NameFile: " + e.getMessage());
        }
        return studentMap;
    }

    public static List<Student> readCourseData(String path, Map<String, String> studentMap) {
        Map<String, Student> studentObjects = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                String id = parts[0].trim();
                String name = studentMap.getOrDefault(id, "UNKNOWN");
                String course = parts[1].trim();

                Float t1 = Float.parseFloat(parts[2]);
                Float t2 = Float.parseFloat(parts[3]);
                Float t3 = Float.parseFloat(parts[4]);
                Float fe = Float.parseFloat(parts[5]);

                Student student = studentObjects.getOrDefault(id, new Student(id, name));
                student.addGrade(new Grade(course, t1, t2, t3, fe));
                studentObjects.put(id, student);
            }
        } catch (IOException e) {
            System.err.println("Error reading CourseFile: " + e.getMessage());
        }

        return new ArrayList<>(studentObjects.values());
    }

    public static void writeOutput(String path, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Student ID,Student Name,Course Code,Final Grade");
            bw.newLine();

            for (Student s : students) {
                for (Grade g : s.getGrades()) {
                    float finalGrade = g.calculateFinalGrade();
                    bw.write(String.format("%s, %s, %s, %.1f",
                        s.getStudentId(), s.getStudentName(),
                        g.getCourseCode(), finalGrade));
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Error writing OutputFile: " + e.getMessage());
        }
    }
}
