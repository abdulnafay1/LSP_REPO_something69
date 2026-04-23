# Question 2 Answers

## Design Explanation

The `Report` abstract class implements the Template Method pattern by defining `generateReport()` as a `final` method that lays out a fixed workflow: `loadData()`, followed by `formatHeader()`, `formatBody()`, and `formatFooter()`. Each of those four steps is declared `abstract`, meaning every subclass must supply its own implementation while the overall sequence remains locked in the parent class. `StudentReport` and `CourseReport` each override these abstract methods with their own data and formatting logic, without touching the structure of the algorithm. The `Driver` class demonstrates polymorphism by storing both report types in a `List<Report>` and calling `generateReport()` on each, letting the correct subclass behavior execute at runtime without the driver needing to know which type it is dealing with.
