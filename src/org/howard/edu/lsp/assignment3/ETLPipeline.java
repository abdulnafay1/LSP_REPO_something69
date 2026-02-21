package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Orchestrates the full Extract → Transform → Load pipeline.
 *
 * <p>This class coordinates the three phases of the ETL process by delegating
 * each phase to its specialist class ({@link CSVReader}, {@link ProductTransformer},
 * and {@link CSVWriter}).  It also collects run-statistics and prints the
 * summary that matches Assignment 2's output exactly.</p>
 *
 * <p>Usage:</p>
 * <pre>{@code
 *   ETLPipeline pipeline = new ETLPipeline("data/products.csv",
 *                                          "data/transformed_products.csv");
 *   pipeline.run();
 * }</pre>
 *
 * @author Abdul Nafay Saleem
 */
public class ETLPipeline {

    /** Relative path to the input CSV file. */
    private final String inputPath;

    /** Relative path to the output CSV file. */
    private final String outputPath;

    /**
     * Constructs an ETLPipeline with the specified input and output paths.
     *
     * @param inputPath  path to the source CSV file
     * @param outputPath path where the transformed CSV will be written
     */
    public ETLPipeline(String inputPath, String outputPath) {
        this.inputPath  = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Executes the full ETL pipeline: Extract, Transform, and Load.
     *
     * <p>Steps performed:</p>
     * <ol>
     *   <li><strong>Extract</strong> – {@link CSVReader} reads and validates
     *       the input file.</li>
     *   <li><strong>Transform</strong> – {@link ProductTransformer} applies
     *       all business rules to the parsed products.</li>
     *   <li><strong>Load</strong> – {@link CSVWriter} writes the results to
     *       the output file.</li>
     *   <li>Prints a run-summary matching Assignment 2's format.</li>
     * </ol>
     *
     * <p>If the input file is missing, an error message is printed and the
     * method returns without producing output.  I/O errors are caught and
     * reported without a stack trace.</p>
     */
    public void run() {
        CSVReader reader = new CSVReader(inputPath);

        // ── Extract ────────────────────────────────────────────────────────────
        boolean fileFound;
        try {
            fileFound = reader.read();
        } catch (IOException e) {
            System.out.println("ERROR: I/O failure while processing files.");
            return;
        }

        if (!fileFound) {
            System.out.println("ERROR: Missing input file: " + inputPath);
            return;
        }

        List<Product> products = reader.getProducts();
        int rowsRead    = reader.getRowsRead();
        int rowsSkipped = reader.getRowsSkipped();

        // ── Transform ──────────────────────────────────────────────────────────
        ProductTransformer transformer = new ProductTransformer();
        transformer.transformAll(products);

        // ── Load ───────────────────────────────────────────────────────────────
        CSVWriter writer = new CSVWriter(outputPath);
        try {
            writer.write(products);
        } catch (IOException e) {
            System.out.println("ERROR: I/O failure while processing files.");
            return;
        }

        int rowsTransformed = products.size();

        // ── Summary ────────────────────────────────────────────────────────────
        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    /**
     * Prints the run summary to standard output.
     *
     * @param rowsRead        total non-header lines encountered
     * @param rowsTransformed lines successfully transformed and written
     * @param rowsSkipped     lines skipped due to validation errors
     * @param outputPath      path of the output file that was written
     */
    private void printSummary(int rowsRead, int rowsTransformed,
                               int rowsSkipped, String outputPath) {
        System.out.println("Run Summary");
        System.out.println("Rows read: "        + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: "     + rowsSkipped);
        System.out.println("Output written to: "+ outputPath);
    }
}
