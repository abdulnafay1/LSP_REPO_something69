package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 * Applies a 10% discount.
 *
 * @author Abdul Nafay Saleem
 */
public class MemberPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price for a member customer.
     *
     * @param price the original price
     * @return the price after a 10% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}