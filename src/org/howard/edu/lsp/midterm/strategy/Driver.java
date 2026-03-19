package org.howard.edu.lsp.midterm.strategy;

/**
 * Demonstrates the Strategy Pattern using different pricing strategies.
 *
 * @author Abdul Nafay Saleem
 */
public class Driver {

    /**
     * Runs the program and prints the calculated prices
     * for each customer type.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        double price = 100.0;

        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        calculator.setStrategy(new VipPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}