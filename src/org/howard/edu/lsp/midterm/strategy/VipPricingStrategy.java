package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 * Applies a 20% discount.
 *
 * @author Abdul Nafay Saleem
 */
public class VipPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price for a VIP customer.
     *
     * @param price the original price
     * @return the price after a 20% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}