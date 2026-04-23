package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class that demonstrates polymorphism using the Template Method pattern.
 * Creates a list of Report objects and calls generateReport() on each.
 */
public class Driver {

    /**
     * Main method to demonstrate the Template Method pattern.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
            System.out.println();
        }
    }
}
