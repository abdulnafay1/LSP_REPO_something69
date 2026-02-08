/**
 * Name: Abdul Nafay Saleem
 */
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        File inputFile = new File(INPUT_PATH);

        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("ERROR: Missing input file: " + INPUT_PATH);
            return; // clean exit, no stack trace
        }

        int rowsRead = 0;        // non-header lines encountered (including bad ones)
        int rowsSkipped = 0;     // bad/blank/parse-error/field-count lines
        int rowsTransformed = 0; // written to output

        File outputFile = new File(OUTPUT_PATH);
        File outputDir = outputFile.getParentFile();
        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
        }

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(inputFile));
            bw = new BufferedWriter(new FileWriter(outputFile));

            String header = br.readLine(); // header row (may be null if file is empty)
            // Always write header row to output (even if input is header-only)
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            if (header == null) {
                // Empty file (no header at all). Spec only mentions "header only",
                // but this still behaves safely and produces header-only output.
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputFile.getPath());
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                // Row skipping rules: blank line => skip (but counts as read)
                rowsRead++;

                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String productIdRaw = parts[0].trim();
                String nameRaw = parts[1].trim();
                String priceRaw = parts[2].trim();
                String categoryRaw = parts[3].trim();

                int productId;
                BigDecimal priceOriginal;

                try {
                    productId = Integer.parseInt(productIdRaw);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    // BigDecimal from string avoids floating point issues
                    priceOriginal = new BigDecimal(priceRaw);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // TRANSFORM (exact order)

                // 1) Convert name to UPPERCASE
                String nameFinal = nameRaw.toUpperCase();

                // Keep original category for rule #3
                String categoryOriginal = categoryRaw;

                // 2) If category is "Electronics", apply 10% discount to price
                BigDecimal priceAfterDiscount = priceOriginal;
                boolean wasElectronics = "Electronics".equals(categoryOriginal);
                if (wasElectronics) {
                    priceAfterDiscount = priceOriginal.multiply(new BigDecimal("0.90"));
                }

                // Round-half-up to exactly two decimals (final rounded price)
                BigDecimal finalRoundedPrice = priceAfterDiscount.setScale(2, RoundingMode.HALF_UP);

                // 3) If final rounded price > 500.00 AND original category was "Electronics",
                //    change category to "Premium Electronics"
                String categoryFinal = categoryOriginal;
                if (wasElectronics && finalRoundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                    categoryFinal = "Premium Electronics";
                }

                // 4) Add PriceRange based on final rounded price
                String priceRange = determinePriceRange(finalRoundedPrice);

                // LOAD: write transformed row
                // Price must always be written with exactly two decimal places
                String outputRow = productId + "," + nameFinal + "," + finalRoundedPrice.toPlainString()
                        + "," + categoryFinal + "," + priceRange;

                bw.write(outputRow);
                bw.newLine();
                rowsTransformed++;
            }

            printSummary(rowsRead, rowsTransformed, rowsSkipped, outputFile.getPath());

        } catch (IOException e) {
            // Keep it clear and clean (no stack trace)
            System.out.println("ERROR: I/O failure while processing files.");
        } finally {
            // close safely
            try {
                if (br != null) br.close();
            } catch (IOException ignored) { }
            try {
                if (bw != null) bw.close();
            } catch (IOException ignored) { }
        }
    }

    private static String determinePriceRange(BigDecimal price) {
        // Uses final rounded price
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, String outputPath) {
        System.out.println("Run Summary");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }
}
