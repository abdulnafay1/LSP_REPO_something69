package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Handles the <strong>Transform</strong> phase of the ETL pipeline.
 *
 * <p>Applies all business-rule transformations to each {@link Product} in the
 * supplied list, mutating the objects in place:</p>
 * <ol>
 *   <li>Convert {@code name} to upper-case.</li>
 *   <li>If {@code category} is {@code "Electronics"}, apply a 10 % discount
 *       and round the result to exactly two decimal places
 *       (HALF_UP rounding).</li>
 *   <li>If the final rounded price is greater than {@code 500.00} <em>and</em>
 *       the original category was {@code "Electronics"}, upgrade the category
 *       to {@code "Premium Electronics"}.</li>
 *   <li>Assign a {@code priceRange} label based on the final rounded price:
 *       {@code "Low"} (≤ 10), {@code "Medium"} (≤ 100), {@code "High"}
 *       (≤ 500), or {@code "Premium"} (&gt; 500).</li>
 * </ol>
 *
 * @author Abdul Nafay Saleem
 */
public class ProductTransformer {

    /**
     * Transforms every product in the supplied list in place.
     *
     * <p>The list is modified directly; no new list is returned.</p>
     *
     * @param products list of {@link Product} objects to transform
     */
    public void transformAll(List<Product> products) {
        for (Product p : products) {
            transform(p);
        }
    }

    /**
     * Applies all transformation rules to a single {@link Product}.
     *
     * @param product the product to transform (mutated in place)
     */
    public void transform(Product product) {
        // Rule 1: upper-case the name
        product.setName(product.getName().toUpperCase());

        boolean wasElectronics = "Electronics".equals(product.getCategory());

        // Rule 2: 10% discount for Electronics, then round to 2 decimal places
        BigDecimal price = product.getPrice();
        if (wasElectronics) {
            price = price.multiply(new BigDecimal("0.90"));
        }
        BigDecimal finalPrice = price.setScale(2, RoundingMode.HALF_UP);
        product.setPrice(finalPrice);

        // Rule 3: upgrade category if Electronics and final price > 500.00
        if (wasElectronics && finalPrice.compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }

        // Rule 4: assign price range
        product.setPriceRange(determinePriceRange(finalPrice));
    }

    /**
     * Determines the price-range bucket for a given price.
     *
     * @param price the final rounded price
     * @return {@code "Low"} if price ≤ 10.00,
     *         {@code "Medium"} if price ≤ 100.00,
     *         {@code "High"} if price ≤ 500.00,
     *         {@code "Premium"} otherwise
     */
    public String determinePriceRange(BigDecimal price) {
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
}
