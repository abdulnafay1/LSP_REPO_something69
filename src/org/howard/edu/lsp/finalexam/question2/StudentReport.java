package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for student information.
 * Extends Report and provides student-specific implementations
 * for each step of the report workflow.
 */
public class StudentReport extends Report {

    /** The name of the student. */
    private String studentName;

    /** The GPA of the student. */
    private double gpa;

    /**
     * Loads student data used in the report.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Formats and prints the student report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Student Report");
    }

    /**
     * Formats and prints the student report body.
     */
    @Override
    protected void formatBody() {
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
    }

    /**
     * Formats and prints the student report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Student Report");
    }
}
