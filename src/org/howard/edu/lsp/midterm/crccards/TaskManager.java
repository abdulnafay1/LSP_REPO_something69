package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Uses a LinkedHashMap to prevent duplicate IDs and support lookup by taskId.
 * @author [Your Name]
 */
public class TaskManager {
    private Map<String, Task> tasks = new LinkedHashMap<>();

    /**
     * Adds a task to the manager.
     * @param task the task to add
     * @throws IllegalArgumentException if a task with the same ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID: " + task.getTaskId());
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its ID.
     * @param taskId the ID to look up
     * @return the Task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.getOrDefault(taskId, null);
    }

    /**
     * Returns all tasks matching the given status.
     * @param status the status to filter by
     * @return list of matching tasks
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks.values()) {
            if (t.getStatus().equals(status)) {
                result.add(t);
            }
        }
        return result;
    }
}