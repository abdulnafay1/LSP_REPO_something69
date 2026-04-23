# Question 5 Answers

## Heuristic 1

**Name:** H2.1 - All data should be hidden within its class.

**Explanation:** This heuristic states that instance variables should never be exposed publicly and should only be accessible through a class's public interface. It improves maintainability because internal data can be changed without breaking code that depends on the class. In lecture, this was presented as the foundation of many coding standards, specifically the practice of making all data attributes private. The professor noted that this heuristic, along with H2.8, forms the basis of how well-encapsulated classes should be structured.

---

## Heuristic 2

**Name:** H3.2 - Do not create god classes in your system.

**Explanation:** This heuristic states that no single class should concentrate too much of the system's data or logic. It improves maintainability by distributing responsibilities across classes so that changes to one part of the system do not ripple through everything else. In lecture, this was illustrated using Arthur Riel's home heating system example. The HeatFlowRegulator was shown as a god class that pulled all information from Room (DesiredTemp, ActualTemp, Occupancy) and made all decisions itself. The improved design moved that logic into the Room class so it could answer do_I_need_heat() on its own, making HeatFlowRegulator much simpler. The professor also noted that god classes often have names ending in "System", "Manager", or "Driver" as a warning sign.

---

## Heuristic 3

**Name:** H2.8 - A class should capture one and only one key abstraction.

**Explanation:** This heuristic states that each class should represent a single, clearly defined concept and should not mix together multiple unrelated responsibilities. It improves readability by making it obvious what each class is responsible for, and improves maintainability by keeping changes localized to the class they belong in. In lecture, this was discussed alongside the proliferation of classes problem as the counterbalance: while you do not want god classes that do too much, you also do not want classes that are too small and scattered. A well-designed class lands in the middle, capturing exactly one abstraction cleanly.
