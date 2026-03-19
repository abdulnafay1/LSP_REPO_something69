# Development Log – Question 2

## External Resources Used

### AI Tool
**Resource:** ChatGPT

**Purpose:**  
Used briefly to confirm understanding of object-oriented design issues and to check whether the proposed class decomposition for the CRC redesign made sense.

---

## Conversation Transcript

**User:**  
I'm analyzing a class called `OrderProcessor` that calculates tax, prints a receipt, writes to a file, sends an email, and logs activity all inside one method. Is that considered poor object-oriented design?

**ChatGPT:**  
Yes. When one class handles many unrelated responsibilities, it often indicates low cohesion and a violation of the Single Responsibility Principle. A class should ideally have one clear responsibility, while other tasks such as persistence, printing, or notifications should be handled by separate classes.

---

**User:**  
Would separating things like tax calculation, receipt printing, and email sending into different classes be a better design?

**ChatGPT:**  
Yes. For example, you might have classes like `TaxCalculator`, `ReceiptPrinter`, `EmailService`, and a repository class responsible for saving orders. The main processor could then coordinate these components rather than implementing all functionality itself.

---

**User:**  
If I create a class like `Order` to hold the customer name, email, item, and price, should that class do the calculations too?

**ChatGPT:**  
Typically the `Order` class would only store order data. Calculation logic such as tax or discounts is often handled by separate service classes so that responsibilities remain clearly separated.

---

**User:**  
So a coordinator class like `OrderProcessor` could just call those other classes instead of doing everything itself?

**ChatGPT:**  
Exactly. The processor can act as a controller that coordinates the workflow while delegating specific tasks to specialized classes.
