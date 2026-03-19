package org.howard.edu.lsp.midterm.crccards;

import java.util.Set;

/**
 * Represents a task with an ID, description, and status.
 * @author Abdul Nafay Saleem
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    private static final Set<String> VALID_STATUSES = Set.of("OPEN", "IN_PROGRESS", "COMPLETE");

    /**
     * Constructs a Task with OPEN as the default status.
     * @param taskId unique identifier for the task
     * @param description brief description of the task
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /** @return the task ID */
    public String getTaskId() { return taskId; }

    /** @return the task description */
    public String getDescription() { return description; }

    /** @return the current status */
    public String getStatus() { return status; }

    /**
     * Sets the status. Sets to UNKNOWN if the value is not valid.
     * @param status the new status value
     */
    public void setStatus(String status) {
        this.status = VALID_STATUSES.contains(status) ? status : "UNKNOWN";
    }

    /**
     * Returns a string in the format: taskId description [status]
     * @return formatted string representation
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}