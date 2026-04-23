# Question 1 Answers

## Part 1: Shared Resources and Risk

**Shared Resource #1:** `nextId`, the integer counter used to generate unique request IDs. It is an instance variable shared across all threads that call `getNextId()`.

**Shared Resource #2:** `requests`, the `ArrayList<String>` that stores all submitted requests. Multiple threads can attempt to add to this list simultaneously.

**Concurrency Problem:** Race condition. Two or more threads can read the same value of `nextId` before either one increments it, resulting in duplicate IDs being assigned to different requests. Similarly, simultaneous writes to the `ArrayList` can cause corrupted or lost entries.

**Why addRequest() is unsafe:** `addRequest()` performs two separate, non-atomic operations: calling `getNextId()` and then calling `requests.add()`. Because these are not executed as a single atomic unit, a context switch can occur between the two steps. Another thread may interleave, causing duplicate IDs or list corruption.

---

## Part 2: Evaluate Fixes

**Fix A:** `public synchronized int getNextId() { ... }`

Not correct. Synchronizing only `getNextId()` protects the ID counter from duplicate reads, but it does not protect `addRequest()` as a whole. Two threads can still interleave between the call to `getNextId()` and the call to `requests.add()`, leading to out-of-order inserts or list corruption. The fix is too narrow and addresses only part of the critical section.

**Fix B:** `public synchronized void addRequest(String studentName) { ... }`

Correct. Since `addRequest()` is the only method that both reads/increments `nextId` and writes to `requests`, synchronizing it ensures the entire operation is atomic. Only one thread at a time can enter the method, eliminating the race condition on both shared resources.

**Fix C:** `public synchronized List<String> getRequests() { ... }`

Not correct. Synchronizing `getRequests()` only locks during the return of the list reference, but does nothing to protect the concurrent writes in `addRequest()`. The race condition on `nextId` and `requests.add()` is completely unaddressed. Additionally, returning the raw `ArrayList` reference allows external callers to mutate the list without any synchronization, creating a further thread-safety problem.

---

## Part 3: Object-Oriented Design

**Answer:** No, `getNextId()` should not be public.

**Explanation:** According to Arthur Riel's heuristics, a class should minimize its public interface and hide implementation details from the outside world. `getNextId()` is an internal helper used solely by `addRequest()` to generate IDs. It is an implementation detail, not a service the class intends to offer to callers. Making it public exposes internal state management unnecessarily, increases coupling, and allows external code to misuse or interfere with the ID generation logic. It should be declared `private`.

---

## Part 4: Alternative Synchronization Approach

**Description:**

Instead of using the `synchronized` keyword, we can use classes from the `java.util.concurrent` package. Specifically, `AtomicInteger` provides a thread-safe counter whose `getAndIncrement()` method is a single atomic compare-and-set (CAS) operation at the hardware level, so no explicit locking is required. For the list, `CopyOnWriteArrayList` provides a thread-safe list implementation that handles concurrent writes safely. Together, these eliminate the race condition in `addRequest()` without any explicit synchronization blocks. Note that `CopyOnWriteArrayList` is best suited for read-heavy scenarios, as each write creates a full copy of the underlying array. For a write-heavy system, `Collections.synchronizedList(new ArrayList<>())` could be a more efficient alternative, though it would require external synchronization for compound operations.

**Code Snippet:**

```java
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private List<String> requests = new CopyOnWriteArrayList<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        requests.add("Request-" + id + " from " + studentName);
    }

    public List<String> getRequests() {
        return requests;
    }
}
```
