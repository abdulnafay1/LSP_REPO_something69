package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a single product record in the ETL pipeline.
 *
 * <p>This class acts as a plain data object (model) that holds all fields
 * for one row of the CSV: product ID, name, price, category, and price range.
 * It is populated by {@link CSVReader} and mutated by {@link ProductTransformer}
 * before being written by {@link CSVWriter}.</p>
 *
 * @author Abdul Nafay Saleem
 */
public class Product {

    /** Unique numeric identifier for the product. */
    private int productId;

    /** Display name of the product. */
    private String name;

    /** Price of the product, stored with exact decimal precision. */
    private BigDecimal price;

    /** Category label (e.g. "Electronics", "Premium Electronics"). */
    private String category;

    /**
     * Computed price-range bucket: "Low", "Medium", "High", or "Premium".
     * Set by {@link ProductTransformer} after the final price is known.
     */
    private String priceRange;

    /**
     * Constructs a Product with the four raw fields read directly from the CSV.
     * The {@code priceRange} field is left null until the transformer sets it.
     *
     * @param productId numeric product identifier
     * @param name      product name (raw, before upper-casing)
     * @param price     product price as a {@link BigDecimal}
     * @param category  product category string
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Returns the product ID.
     *
     * @return integer product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the product name.
     *
     * @return product name string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name (used by the transformer to upper-case it).
     *
     * @param name new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price.
     *
     * @return price as {@link BigDecimal}
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price (used by the transformer after applying discounts).
     *
     * @param price new price value
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the product category.
     *
     * @return category string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category (used by the transformer to upgrade to
     * "Premium Electronics" when applicable).
     *
     * @param category new category value
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the price-range bucket assigned by the transformer.
     *
     * @return price range label ("Low", "Medium", "High", or "Premium"),
     *         or {@code null} if the transformer has not run yet
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets the price-range bucket.
     *
     * @param priceRange price range label
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
}
