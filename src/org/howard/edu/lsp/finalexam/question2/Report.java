package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class representing a report.
 * Uses the Template Method pattern to define a fixed workflow
 * while allowing subclasses to provide specific implementations
 * for each step.
 */
public abstract class Report {

    /**
     * Template method that defines the fixed report generation workflow.
     * Subclasses cannot override this method.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        formatHeader();
        System.out.println("=== BODY ===");
        formatBody();
        System.out.println("=== FOOTER ===");
        formatFooter();
    }

    /**
     * Loads the data needed for the report.
     * Must be implemented by subclasses.
     */
    protected abstract void loadData();

    /**
     * Formats and prints the report header.
     * Must be implemented by subclasses.
     */
    protected abstract void formatHeader();

    /**
     * Formats and prints the report body.
     * Must be implemented by subclasses.
     */
    protected abstract void formatBody();

    /**
     * Formats and prints the report footer.
     * Must be implemented by subclasses.
     */
    protected abstract void formatFooter();
}
