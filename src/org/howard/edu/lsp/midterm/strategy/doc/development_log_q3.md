# Development Log – Question 3

## External Resources Used

### AI Tool
**Resource:** ChatGPT

**What I asked:**  
I asked how to improve a `PriceCalculator` design that used multiple conditional statements for different customer types. I also asked whether the Strategy Pattern would be appropriate for separating discount behaviors into different classes.

**How it helped:**  
The responses helped confirm that each pricing rule should be implemented as its own strategy class and that the `PriceCalculator` class should delegate the calculation to the selected strategy. This helped me organize the refactored solution more clearly.

---

## Conversation Transcript

**User:**  
I have a `PriceCalculator` class that uses several `if` statements for REGULAR, MEMBER, VIP, and HOLIDAY customer types. Is that considered bad design?

**ChatGPT:**  
It can become poor design as the number of customer types grows. Each pricing rule is a separate behavior, so placing them all in one conditional chain makes the class harder to extend and maintain.

---

**User:**  
Would the Strategy Pattern make sense here?

**ChatGPT:**  
Yes. The Strategy Pattern is a good fit when you have multiple interchangeable algorithms or behaviors, such as different pricing rules for different customer types.

---

**User:**  
So should each customer type have its own class?

**ChatGPT:**  
Yes. You could create separate classes such as `RegularPricingStrategy`, `MemberPricingStrategy`, `VipPricingStrategy`, and `HolidayPricingStrategy`, all implementing the same interface.

---

**User:**  
Then what should `PriceCalculator` do?

**ChatGPT:**  
`PriceCalculator` should act as the context class. It should store a reference to the current strategy and delegate the calculation to that strategy instead of implementing all pricing rules directly.

