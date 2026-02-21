package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the <strong>Extract</strong> phase of the ETL pipeline.
 *
 * <p>Reads {@code data/products.csv}, skips blank lines and rows with wrong
 * field counts or un-parseable values, and returns a list of {@link Product}
 * objects ready for transformation. Counters for rows read and rows skipped
 * are available after {@link #read()} completes.</p>
 *
 * @author Abdul Nafay Saleem
 */
public class CSVReader {

    /** Path to the input CSV file. */
    private final String inputPath;

    /** Products successfully parsed from the file. */
    private final List<Product> products = new ArrayList<>();

    /** Total non-header lines encountered (including bad ones). */
    private int rowsRead = 0;

    /** Lines that were skipped due to validation failures. */
    private int rowsSkipped = 0;

    /**
     * Constructs a CSVReader for the given file path.
     *
     * @param inputPath relative or absolute path to the input CSV
     */
    public CSVReader(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reads and parses the input CSV file.
     *
     * <p>The method resets internal counters on each call, so it is safe to
     * call more than once (though not necessary in normal use).  Any row that
     * is blank, has the wrong number of fields, or contains an un-parseable
     * ProductID or Price is counted as skipped.</p>
     *
     * @return {@code true} if the file existed and was opened successfully,
     *         {@code false} if the file is missing
     * @throws IOException if an I/O error occurs while reading the file
     */
    public boolean read() throws IOException {
        products.clear();
        rowsRead = 0;
        rowsSkipped = 0;

        File file = new File(inputPath);
        if (!file.exists() || !file.isFile()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine(); // consume header row

            if (header == null) {
                // Completely empty file — treat as header-only, return no products
                return true;
            }

            String line;
            while ((line = br.readLine()) != null) {
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
                String nameRaw      = parts[1].trim();
                String priceRaw     = parts[2].trim();
                String categoryRaw  = parts[3].trim();

                int productId;
                BigDecimal price;

                try {
                    productId = Integer.parseInt(productIdRaw);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    price = new BigDecimal(priceRaw);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                products.add(new Product(productId, nameRaw, price, categoryRaw));
            }
        }

        return true;
    }

    /**
     * Returns the list of products successfully parsed during the last
     * {@link #read()} call.
     *
     * @return list of {@link Product} objects; empty if none were valid
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the total number of non-header lines encountered during the
     * last {@link #read()} call (including skipped lines).
     *
     * @return rows read count
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of lines skipped due to validation errors during
     * the last {@link #read()} call.
     *
     * @return rows skipped count
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}
