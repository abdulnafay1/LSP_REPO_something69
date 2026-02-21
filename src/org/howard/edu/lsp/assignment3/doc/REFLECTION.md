# Reflection – Assignment 3: Object-Oriented Redesign of the ETL Pipeline

**Author:** Abdul Nafay Saleem  
**Course:** CSCI 363  

---

## 1. Overview

Assignment 2 delivered a fully functional ETL pipeline in a single Java class.
Assignment 3 keeps every input, output, transformation rule, and error-handling
behavior identical, but restructures the code into six focused classes that each
own one well-defined responsibility.

---

## 2. What Is Different About the Design?

### Assignment 2
All logic lived in one class, `ETLPipeline`, as a collection of `static` methods
and a `main` entry point. Reading, validation, transformation, writing, and
summary printing were interleaved inside a single `try` block. The `Product`
concept did not exist as a type — data was passed around as raw `String[]` arrays
and local variables. Adding a new transformation rule or swapping the file format
would require editing code alongside unrelated logic.

### Assignment 3
The same work is now split across six classes:

| Class | Phase / Role |
|---|---|
| `Product` | Data model — holds one row's fields |
| `CSVReader` | Extract — reads and validates the CSV |
| `ProductTransformer` | Transform — applies all business rules |
| `CSVWriter` | Load — writes the output CSV |
| `ETLPipeline` | Orchestrator — coordinates the three phases |
| `ETLApp` | Entry point — owns only the `main` method |

Each class is independently readable and testable. Changing the output format
(e.g., writing JSON instead of CSV) means replacing only `CSVWriter` without
touching transformation logic.

---

## 3. Which OO Concepts Were Applied?

### Objects and Classes
`Product` is a first-class object. Instead of tracking seven local variables
per row, a single `Product` instance travels through the pipeline carrying its
own state. `CSVReader`, `ProductTransformer`, and `CSVWriter` are also stateful
objects — the reader remembers row counts, the writer remembers its output path.

### Encapsulation
`Product` hides all its fields behind private declarations and exposes them only
through public getters and setters. Callers cannot accidentally corrupt a field
by accessing the array at the wrong index, as was possible in Assignment 2.
`CSVReader` similarly encapsulates its `BufferedReader` and counters; the
pipeline never touches file handles directly.

### Single Responsibility (closely related to encapsulation)
Each class has one reason to change. If the business rules change, only
`ProductTransformer` is edited. If the CSV format changes, only `CSVReader`
and `CSVWriter` change. In Assignment 2 every change required editing the same
monolithic class.

### Polymorphism (foundational use)
`ProductTransformer` exposes both `transformAll(List<Product>)` and the
lower-level `transform(Product)`. This makes it straightforward for a subclass
to override `transform` in the future — for example, a `DiscountedTransformer`
that applies a different discount rate — without changing the pipeline or the
reader/writer at all.

### Inheritance (potential extension point)
While A3 does not introduce subclasses beyond the base design, the architecture
is explicitly inheritance-ready. `ProductTransformer` could be an abstract base
class with a `transform(Product)` template method, and concrete subclasses could
supply different business rules. This is a common OO pattern that the flat A2
design made impossible without large-scale refactoring.

---

## 4. Testing: Confirming A3 Produces Identical Output to A2

### Normal Input
Both programs were run against the same `data/products.csv`. The
`data/transformed_products.csv` files produced were compared line-by-line using
a diff tool and found to be byte-identical.

### Missing Input File
The input file was temporarily renamed. Both A2 and A3 printed:
```
ERROR: Missing input file: data/products.csv
```
and exited cleanly without a stack trace or output file.

### Empty Input File (header only)
An empty file containing only the header row was used. Both versions produced
an output file with only the header:
```
ProductID,Name,Price,Category,PriceRange
```
and printed a Run Summary showing 0 rows read, 0 transformed, 0 skipped.

### Bad Rows
Rows with missing fields, non-numeric IDs, and non-numeric prices were tested.
Both versions skipped the same rows and produced the same skipped-row counts.

### Conclusion
All four test cases produced identical console output and identical (or absent)
output files, confirming that the OO redesign is a pure structural change with
no behavioral differences.

---

## 5. Summary

Assignment 3 demonstrates that the same correct behavior can be expressed in a
design that is far easier to understand, maintain, and extend. The key gains are
encapsulation (data hidden inside `Product`), separation of concerns (one class
per ETL phase), and a pipeline orchestrator that reads like a description of the
process rather than an implementation of it. These are the foundations of
object-oriented software design.
