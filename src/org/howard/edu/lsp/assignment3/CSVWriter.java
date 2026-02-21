package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles the <strong>Load</strong> phase of the ETL pipeline.
 *
 * <p>Writes the transformed {@link Product} list to
 * {@code data/transformed_products.csv}, always including the header row.
 * The output directory is created automatically if it does not exist.</p>
 *
 * @author Abdul Nafay Saleem
 */
public class CSVWriter {

    /** Path to the output CSV file. */
    private final String outputPath;

    /**
     * Constructs a CSVWriter that writes to the given file path.
     *
     * @param outputPath relative or absolute path for the output CSV
     */
    public CSVWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Writes the header row followed by one row per product to the output file.
     *
     * <p>The output directory is created if it does not already exist.
     * Price values are always written with exactly two decimal places.</p>
     *
     * @param products list of fully-transformed {@link Product} objects to write
     * @throws IOException if an I/O error occurs while writing
     */
    public void write(List<Product> products) throws IOException {
        File outputFile = new File(outputPath);
        File outputDir  = outputFile.getParentFile();

        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            // Always write the header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            for (Product p : products) {
                String row = p.getProductId()
                        + "," + p.getName()
                        + "," + p.getPrice().toPlainString()
                        + "," + p.getCategory()
                        + "," + p.getPriceRange();
                bw.write(row);
                bw.newLine();
            }
        }
    }

    /**
     * Returns the output file path this writer was configured with.
     *
     * @return output path string
     */
    public String getOutputPath() {
        return outputPath;
    }
}
