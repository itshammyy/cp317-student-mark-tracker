import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String nameFile = "../input/NameFile.txt";
        String courseFile = "../input/CourseFile.txt";
        String outputFile = "../output/OutputFile.txt";

        ArrayList<Student> studentLog = FileProcessor.readStudentNames(nameFile); // Read student names from file
        FileProcessor.readCourseData(courseFile, studentLog); // Read course data and update student log
        FileProcessor.writeOutput(outputFile, studentLog);    // Write the output to a file

        System.out.println("Output written to: " + outputFile);
    }
}
