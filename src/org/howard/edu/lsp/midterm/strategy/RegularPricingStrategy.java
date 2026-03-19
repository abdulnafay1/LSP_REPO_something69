package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 * No discount is applied.
 *
 * @author Abdul Nafay Saleem
 */
public class RegularPricingStrategy implements PricingStrategy {

    /**
     * Returns the original price with no discount.
     *
     * @param price the original price
     * @return the unchanged price
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}