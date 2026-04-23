# AI Usage

## AI Tools Used
Claude (claude.ai)

## Prompts Used
1. "What is the difference between abstract and final in Java?"
2. "How does the Template Method pattern use the final keyword to lock down the algorithm?"
3. "How do I use polymorphism with a List of abstract types in Java?"

## How AI Helped
AI helped clarify the role of the `final` keyword in locking the template method so subclasses cannot override the overall workflow. It also confirmed the correct way to store multiple subclass objects in a `List<Report>` to demonstrate polymorphism. The class structure, method design, and design explanation were worked out independently based on lecture material and the exam requirements.

## Reflection
AI was most useful for confirming syntax and Java-specific details rather than driving the design decisions. Understanding why `generateReport()` needs to be `final` and why the other methods need to be `abstract` came from working through the pattern in lecture first.
