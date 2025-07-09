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

    /**
     * Reads student names from a file and returns a map of student ID to student name.
     * @param path The path to the NameFile.
     * @return A map containing student IDs as keys and student names as values.
     */
    public static Map<String, String> readStudentNames(String path) {
        Map<String, String> studentMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                    studentMap.put(parts[0].trim(), parts[1].trim());
                } else {
                    System.err.println("Invalid line in NameFile: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading NameFile: " + e.getMessage());
        }
        return studentMap;
    }

    /**
     * Reads course data from a file and creates a list of Student objects with their grades.
     * @param path The path to the CourseFile.
     * @param studentMap A map of student IDs to names.
     * @return A list of Student objects with their grades.
     */
    public static List<Student> readCourseData(String path, Map<String, String> studentMap) {
        Map<String, Student> studentObjects = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String id = parts[0].trim();
                    String name = studentMap.getOrDefault(id, "UNKNOWN");
                    if (!name.equals("UNKNOWN")) {
                        Float t1;
                        Float t2;
                        Float t3;
                        Float fe;
                        String course = parts[1].trim();
                        try {
                            t1 = Float.parseFloat(parts[2]);
                            t2 = Float.parseFloat(parts[3]);
                            t3 = Float.parseFloat(parts[4]);
                            fe = Float.parseFloat(parts[5]);
                            Student student = studentObjects.getOrDefault(id, new Student(id, name));
                            student.addGrade(new Grade(course, t1, t2, t3, fe));
                            studentObjects.put(id, student);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid grade format in CourseFile for student ID: " + id);
                            line = br.readLine();
                            continue;
                        }
                    } else {
                        System.err.println("Student ID not found in NameFile: " + id);                        
                    }
                }
                else {
                    System.err.println("Invalid line in CourseFile: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CourseFile: " + e.getMessage());
        }

        return new ArrayList<>(studentObjects.values());
    }

    /*
     * Writes the final grades of students to an output file.
     * Each line contains the student ID, student name, course code, and final grade.
     */
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
