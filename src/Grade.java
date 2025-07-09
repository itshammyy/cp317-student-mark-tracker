/*
 * Grade.java
 * This class represents a student's grades for a course.
 */

public class Grade {
    public String courseCode;
    private Float t1, t2, t3, finalExam; // Encapsulated grades
    public Float finalGrade;

    public Grade(String courseCode, Float t1, Float t2, Float t3, Float finalExam) {
        this.courseCode = courseCode;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.finalExam = finalExam;
    }

    public Float calculateFinalGrade() {
        if (t1 != null && t2 != null && t3 != null && finalExam != null) {
            return (t1 * 0.2f + t2 * 0.2f + t3 * 0.2f + finalExam * 0.4f);  
        }
        throw new IllegalStateException("Missing grades - cannot calculate final grade.");
    }

    public String getCourseCode() {
        return courseCode;
    }
}
