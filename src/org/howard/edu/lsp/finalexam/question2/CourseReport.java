package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for course information.
 * Extends Report and provides course-specific implementations
 * for each step of the report workflow.
 */
public class CourseReport extends Report {

    /** The name of the course. */
    private String courseName;

    /** The enrollment count for the course. */
    private int enrollment;

    /**
     * Loads course data used in the report.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Formats and prints the course report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    /**
     * Formats and prints the course report body.
     */
    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    /**
     * Formats and prints the course report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}
