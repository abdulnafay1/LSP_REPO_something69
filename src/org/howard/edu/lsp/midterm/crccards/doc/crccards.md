# CRC Delegation Explanation

TaskManager collaborates with Task because its core responsibilities, storing tasks,
finding a task by ID, and returning tasks by status, require it to create, access,
and query Task objects. Task, however, only manages its own data (ID, description,
status) and has no need to interact with TaskManager to fulfill its responsibilities.
This one-way dependency keeps Task simple and reusable while TaskManager handles
the coordination logic.