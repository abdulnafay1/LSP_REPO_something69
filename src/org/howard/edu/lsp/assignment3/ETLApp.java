package org.howard.edu.lsp.assignment3;

/**
 * Entry point for the Assignment 3 ETL pipeline.
 *
 * <p>Instantiates an {@link ETLPipeline} with the standard relative paths
 * and calls {@link ETLPipeline#run()} to execute the full
 * Extract → Transform → Load workflow.</p>
 *
 * @author Abdul Nafay Saleem
 */
public class ETLApp {

    /** Relative path to the input CSV file. */
    private static final String INPUT_PATH  = "data/products.csv";

    /** Relative path to the output CSV file. */
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline(INPUT_PATH, OUTPUT_PATH);
        pipeline.run();
    }
}
