package org.howard.edu.lsp.midterm.strategy;

/**
 * Defines a pricing strategy for calculating a final price.
 *
 * @author Abdul Nafay Saleem
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying a pricing rule.
     *
     * @param price the original price
     * @return the final price after applying the strategy
     */
    double calculatePrice(double price);
}