import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nameFile = "../input/NameFile.txt";
        String courseFile = "../input/CourseFile.txt";
        String outputFile = "../output/OutputFile.txt";

        Map<String, String> studentNames = FileProcessor.readStudentNames(nameFile); // Read student names from file
        List<Student> students = FileProcessor.readCourseData(courseFile, studentNames); // Read course data and create Student objects
        FileProcessor.writeOutput(outputFile, students);    // Write the output to a file

        System.out.println("Output written to: " + outputFile);
    }
}
