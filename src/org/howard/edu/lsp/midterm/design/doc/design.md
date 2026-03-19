# Redesigned Architecture — CRC Cards

---
Class: Order
Responsibilities:
  - Store customer name, email, item, and price
  - Provide access to order data
Collaborators: none

---
Class: TaxCalculator
Responsibilities:
  - Calculate tax for a given price
  - Calculate total (price + tax)
Collaborators: Order

---
Class: DiscountService
Responsibilities:
  - Apply discount rules based on order total
Collaborators: Order

---
Class: ReceiptPrinter
Responsibilities:
  - Format and print order receipt to console
Collaborators: Order

---
Class: OrderRepository
Responsibilities:
  - Persist order data to file
Collaborators: Order

---
Class: EmailService
Responsibilities:
  - Send confirmation email to customer
Collaborators: Order

---
Class: ActivityLogger
Responsibilities:
  - Log order processing activity with timestamp
Collaborators: none

---
Class: OrderProcessor
Responsibilities:
  - Coordinate the order processing workflow
Collaborators: Order, TaxCalculator, DiscountService, ReceiptPrinter,
               OrderRepository, EmailService, ActivityLogger