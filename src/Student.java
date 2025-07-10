/*
 * FileProcessor.java
 * This class handles reading student names and course data from files.
 * @method addGrade(): Adds a grade to the student's list of grades.
 * @method getGrades(): Returns the list of grades for the student.
 * @method getStudentId(): Returns the student's ID.
 * @method getStudentName(): Returns the student's name.
 */
import java.util.ArrayList;

public class Student {
    private String studentId;
    private String studentName;
    private ArrayList<Grade> grades;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    // Encapsulated getters for studentId, grades, and studentName
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
