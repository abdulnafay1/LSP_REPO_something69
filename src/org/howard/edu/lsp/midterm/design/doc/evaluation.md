# Design Evaluation: OrderProcessor

## Issues Observed

1. **Poor encapsulation**: All fields (`customerName`, `email`, `item`, `price`)
   are declared `public`, exposing internal state directly. Fields should be private
   with controlled access via getters/setters.

2. **God class / too many responsibilities (Riel Heuristic)**: The single
   `processOrder()` method handles tax calculation, receipt printing, file I/O,
   email notification, discount application, and activity logging. This violates the
   Single Responsibility Principle (a class should have one reason to change.)

3. **Low cohesion**: The method combines unrelated concerns (business logic,
   persistence, UI output, notifications) making the class hard to test, maintain,
   or extend without risking breakage.

4. **Discount applied after saving**: The discount is calculated after the order
   is already written to file, which is a logic error and would cause incorrect
   stored totals.

5. **Hard-coded behavior**: Tax rate (0.07), discount threshold (500), and file
   name ("orders.txt") are all magic numbers/strings with no configuration point,
   making the system inflexible.

6. **Not extensible**: Adding a new notification channel or persistence format
   requires modifying this class directly, violating the Open/Closed Principle.