/**
 * Represents a student's course enrollment and calculates their final grade.
 */
public class Enrollment implements Comparable<Enrollment> {

    private final String studentId;
    private final String studentName;
    private final String courseCode;
    private final double test1;
    private final double test2;
    private final double test3;
    private final double finalExam;
    private double finalGrade;

    // Weights for grade calculation
    private static final double TEST_WEIGHT = 0.2;
    private static final double FINAL_WEIGHT = 0.4;

    /**
     * Creates a new Enrollment object with student and grade details.
     * Calculates final grade upon creation.
     */
    public Enrollment(String studentId, String studentName, String courseCode,
                      double test1, double test2, double test3, double finalExam) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.finalExam = finalExam;
        calculateFinalGrade();
    }

    /**
     * Calculates the final grade using the weighted average formula.
     */
    private void calculateFinalGrade() {
        this.finalGrade = (test1 * TEST_WEIGHT) + (test2 * TEST_WEIGHT) +
                          (test3 * TEST_WEIGHT) + (finalExam * FINAL_WEIGHT);
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getCourseCode() { return courseCode; }
    public double getFinalGrade() { return finalGrade; }

    /**
     * Compares Enrollment objects by student ID for sorting.
     */
    @Override
    public int compareTo(Enrollment other) {
        return this.studentId.compareTo(other.studentId);
    }

    /**
     * Returns formatted string for output.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.1f", studentId, studentName, courseCode, finalGrade);
    }
}
