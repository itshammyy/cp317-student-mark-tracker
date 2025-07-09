import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Grade> grades;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
