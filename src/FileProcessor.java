/*
 * FileProcessor.java
 * This class handles reading student names and course data from files.
 * Uses offencive programming techniques to handle errors gracefully and before they effect the rest of the program.
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    /**
     * Reads student names from a file and returns a map of student ID to student name.
     * @param path The path to the NameFile.
     * @return An ArrayList containing student IDs as keys and student names as values.
     */
    public static ArrayList<Student> readStudentNames(String path) {
        ArrayList<Student> studentLog = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the line has at least two parts and that they aren't empty (ID and Name) --offensive programming
                if (parts.length >= 2 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                    Student student = new Student(parts[0].trim(), parts[1].trim());
                    studentLog.add(student);
                } else {
                    System.err.println("Invalid line in NameFile: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading NameFile: " + e.getMessage());
        }
        return studentLog;
    }

    /**
     * Reads course data from a file and adds all found grades to associated student.
     * @param path The path to the CourseFile.
     * @param studentLog An ArrayList of students.
     * @return An updated list of Student objects with their grades.
     */
    public static void readCourseData(String path, ArrayList<Student> studentLog) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String id = parts[0].trim();
                    Student student = studentLog.stream()
                        .filter(s -> s.getStudentId().equals(id))
                        .findFirst()
                        .orElse(null);
                    if (student != null) {
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
                            
                            // Change Grade type to whatever required, default is TripleGrade
                            student.addGrade(new TripleGrade(course, t1, t2, t3, fe));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid grade format in CourseFile for student ID: " + id);
                            line = br.readLine();
                            continue;
                        }
                    } else {
                        System.err.println("Student ID not found in NameFile: " + id);                        
                    }
                } else {
                    System.err.println("Invalid line in CourseFile: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CourseFile: " + e.getMessage());
        }
    }

    /*
     * Writes the final grades of students to an output file.
     * Each line contains the student ID, student name, course code, and final grade.
     * @param path The path to the OutputFile.
     * @param students A list of Student objects with their grades.
     */
    public static void writeOutput(String path, ArrayList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Student ID, Student Name, Course Code, Final Grade");
            bw.newLine();
            bw.write("=".repeat(50));
            bw.newLine();
            // Sort students by ID for consistent output
            students.sort((s1, s2) -> s1.getStudentId().compareTo(s2.getStudentId()));
            for (Student s : students) {
                if (!s.getGrades().isEmpty()) {            
                    for (Grade g : s.getGrades()) {   // Works due to polymorphism
                        float finalGrade = g.calculateFinalGrade();
                        bw.write(String.format("%s, %s, %s, %.1f",
                            s.getStudentId(), s.getStudentName(),
                            g.getCourseCode(), finalGrade));
                        bw.newLine();
                    }
                } else {
                    System.err.println("No grades found for student ID: " + s.getStudentId());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing OutputFile: " + e.getMessage());
        }
    }
}
