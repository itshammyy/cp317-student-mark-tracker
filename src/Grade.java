/*
 * Grade.java
 * This class represents an abstract class for student's grades for a course.
 * Allows for different types of grading systems to extend it, such as TripleGrade.
 * It contains the course code and a method to calculate the final grade.
 * @method calculateFinalGrade(): Abstract method to be implemented by subclasses to calculate the final grade.
 * @method getCourseCode(): Returns the course code for the grade.
 * @method getFinalGrade(): Returns the final grade for the course.
 */

public abstract class Grade {  // Abstract class for grades
    protected String courseCode;
    protected Float finalGrade;

    public Grade(String courseCode, Float finalExam) {
        this.courseCode = courseCode;
        this.finalGrade = finalExam;  // Initialize final grade with the final exam score
    }

    public abstract Float calculateFinalGrade();  // Abstract method to be implemented by subclasses

    public String getCourseCode() {
        return courseCode;
    }
    
    public Float getFinalGrade() {
        return finalGrade;
    }
}
