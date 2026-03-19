package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday promotions.
 * Applies a 15% discount.
 *
 * @author Abdul Nafay Saleem
 */
public class HolidayPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price for a holiday promotion.
     *
     * @param price the original price
     * @return the price after a 15% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}