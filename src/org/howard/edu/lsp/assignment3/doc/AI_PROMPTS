# AI Prompts & Responses – Assignment 3

**Author:** Abdul Nafay Saleem  
**Course:** CSCI 363  
**AI Tool Used:** Claude (Anthropic) via claude.ai

---

## Prompt 1 – Initial Brainstorm

**Prompt:**
> I have a Java ETL pipeline for Assignment 2 that reads products.csv,
> transforms the data, and writes transformed_products.csv. It's all in one
> class. For Assignment 3 I need to redesign it to be more object-oriented.
> What classes should I break it into?

**AI Response (excerpt):**
The AI suggested splitting the pipeline along the natural ETL boundaries:
- A `Product` class as a data-model object to replace raw `String[]` arrays.
- A `CSVReader` class responsible only for opening the file, parsing lines, and
  returning a `List<Product>`.
- A `ProductTransformer` class that applies all business rules to a list of
  products.
- A `CSVWriter` class that writes the transformed list to the output file.
- An `ETLPipeline` orchestrator that calls the three phases in order.
- An `ETLApp` entry point that only contains `main`.

**What I used / adapted:**
I adopted this class decomposition directly as it mapped cleanly onto the
Extract → Transform → Load structure I already had. I added row-count tracking
to `CSVReader` (rather than the orchestrator) because the reader is the natural
place to know how many lines it encountered.

---

## Prompt 2 – Encapsulation Details

**Prompt:**
> For the Product class, should I use public fields or private fields with
> getters and setters? The transformer needs to mutate name, price, and category.

**AI Response (excerpt):**
The AI recommended private fields with public getters and setters, citing
encapsulation: external code should not be able to bypass validation by writing
directly to a field. Even if there is no validation today, the pattern protects
future changes (e.g., adding a null-check to `setPrice`) without breaking callers.

**What I used / adapted:**
I followed this advice and made all fields private with explicit getters and
setters. I kept the setters simple (no validation) to match A2's behavior, but
the structure now allows validation to be added later.

---

## Prompt 3 – Polymorphism Opportunity

**Prompt:**
> The assignment asks me to use polymorphism. My transformer just applies fixed
> rules. How could I add polymorphism here without over-engineering it?

**AI Response (excerpt):**
The AI suggested keeping the current `ProductTransformer` as a concrete class
but designing it so a subclass could override the `transform(Product)` method.
It noted that exposing both `transformAll(List<Product>)` and `transform(Product)`
as public methods enables this — a subclass overrides `transform` and gets the
loop for free. It also mentioned the alternative of making `ProductTransformer`
abstract, but said that was likely over-engineering for this assignment.

**What I used / adapted:**
I kept the class concrete but made both methods public so overriding is clean.
I documented the extension point in the Javadoc and in this reflection.

---

## Prompt 4 – Javadoc Generation

**Prompt:**
> Can you generate Javadoc comments for the CSVReader class? It has a
> constructor, a read() method that returns boolean, getProducts(),
> getRowsRead(), and getRowsSkipped().

**AI Response (excerpt):**
The AI generated complete `/** ... */` blocks for the class, constructor, and
all five methods, including `@param` and `@return` tags.

**What I used / adapted:**
I reviewed each generated Javadoc block carefully. I edited the `read()` comment
to clarify that the method resets counters on each call (the AI's version did not
mention this). I also corrected the return-value description: the AI said it
returned `false` "on error", but the actual contract is that it returns `false`
only when the file does not exist — I/O errors throw an exception instead.

---

## Prompt 5 – Reflection Draft

**Prompt:**
> Help me outline a 1–2 page reflection comparing my Assignment 2 (single
> monolithic class) to Assignment 3 (six-class OO design). I need to cover:
> what's different, which OO concepts I used, and how I tested that the output
> is the same.

**AI Response (excerpt):**
The AI suggested structuring the reflection in five sections: Overview, Design
Differences (with a table), OO Concepts Applied (one paragraph each for
encapsulation, objects/classes, polymorphism, and inheritance), Testing
Methodology, and a brief Summary.

**What I used / adapted:**
I followed the outline closely but wrote all prose myself, using my own
observations from the actual implementation. I added the specific test cases
(missing file, empty file, bad rows) based on what I actually ran, rather than
the generic test descriptions the AI suggested.
