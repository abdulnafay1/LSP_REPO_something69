# Design Evaluation: PriceCalculator

The current implementation uses a chain of `if` statements to handle each customer
type. This approach has several design problems.

1. **Not extensible**: Adding a new customer type (e.g., STUDENT) requires modifying
the existing method, violating the Open/Closed Principle — classes should be open
for extension but closed for modification.

2. **Poor separation of concerns**: All pricing rules are bundled into a single
method. Each rule is a distinct behavior that should be encapsulated independently,
making them easier to test, swap, and reuse without touching unrelated logic.

3. **Low cohesion and maintainability issues**: As more customer types are added,
the conditional logic becomes longer and harder to read or maintain. This increases
the risk of introducing bugs when modifying the pricing rules.

The Strategy Pattern solves these issues by defining a `PricingStrategy` interface
and implementing each discount rule as its own class.