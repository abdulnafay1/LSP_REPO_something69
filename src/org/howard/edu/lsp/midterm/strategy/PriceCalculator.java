package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates prices using a selected pricing strategy.
 * This class serves as the context in the Strategy Pattern.
 *
 * @author Abdul Nafay Saleem
 */
public class PriceCalculator {
    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the strategy to use for price calculation
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Updates the pricing strategy.
     *
     * @param strategy the new strategy to use
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original price
     * @return the final calculated price
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}