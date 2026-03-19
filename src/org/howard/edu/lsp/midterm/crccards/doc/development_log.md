# Development Log – Question 1

## External Resources Used

### AI Tool
**Resource:** ChatGPT  

**Purpose:**  
Used to verify design choices and clarify some Java implementation details while building the `Task` and `TaskManager` classes for the CRC card assignment.

---

## Conversation Transcript

**User:**  
I am implementing two classes called `Task` and `TaskManager`. The `Task` class stores a task ID, description, and status. The default status should be `"OPEN"`. Does it make sense to set that in the constructor?

**ChatGPT:**  
Yes. Setting the default status inside the constructor is a good approach because it ensures every task starts in a valid initial state when it is created.

---

**User:**  
The assignment says that only `"OPEN"`, `"IN_PROGRESS"`, and `"COMPLETE"` should be valid statuses. What is a clean way to check that when someone sets a status?

**ChatGPT:**  
One common approach is to store the valid values in a `Set` and check if the provided status is in that set. If it is not, you can set the status to `"UNKNOWN"` as required.

---

**User:**  
For the `TaskManager` class I need to store tasks and prevent duplicate IDs. Would a `Map` be better than a list?

**ChatGPT:**  
Yes. Using a `Map<String, Task>` allows you to use the task ID as the key. This makes it easy to check for duplicates and retrieve tasks quickly by ID.

---

**User:**  
The assignment also requires a method to return all tasks with a certain status. How should I implement that?

**ChatGPT:**  
You can iterate through the stored tasks and collect the ones whose status matches the requested value into a `List`, then return that list.

---

**User:**  
For the `toString()` method, it should print something like `T1 Write report [OPEN]`. Is returning a formatted string enough?

**ChatGPT:**  
Yes. Returning a string formatted like `taskId + " " + description + " [" + status + "]"` will satisfy that requirement.
