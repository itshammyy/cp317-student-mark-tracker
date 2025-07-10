/*
 * TripleGrade.java
 * This class represents a student's grades for a course with three tests and a final exam.
 * Inherits from the Grade class and implements the method to calculate the final grade.
 * @method calculateFinalGrade(): Calculates the final grade based on the three tests and final exam.
 */
public class TripleGrade extends Grade { // Inheritance from Grade class
    private Float t1, t2, t3, finalExam; // Encapsulated grades

    public TripleGrade(String courseCode, Float t1, Float t2, Float t3, Float finalExam) {
        super(courseCode, finalExam); // Explicitly call the superclass constructor
        this.courseCode = courseCode;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.finalExam = finalExam;
    }

    public Float calculateFinalGrade() {
        if (t1 != null && t2 != null && t3 != null && finalExam != null) {
            finalGrade = (t1 * 0.2f + t2 * 0.2f + t3 * 0.2f + finalExam * 0.4f);
            finalGrade = Math.round(finalGrade * 10) / 10.0f;
            return finalGrade;
        }
        throw new IllegalStateException("Missing grades - cannot calculate final grade.");
    }
}
