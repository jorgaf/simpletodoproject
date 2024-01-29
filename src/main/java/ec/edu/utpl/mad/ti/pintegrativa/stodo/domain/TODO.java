package ec.edu.utpl.mad.ti.pintegrativa.stodo.domain;

import java.time.LocalDate;

public class TODO {
    private String description;
    private boolean isCompleted;
    private LocalDate createdAt;

    public TODO() {
        this.createdAt = LocalDate.now();
    }

    public TODO(String description) {
        this.description = description;
        this.isCompleted = false;
        this.createdAt = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TODO todo = (TODO) o;

        if (isCompleted != todo.isCompleted) return false;
        if (!description.equals(todo.description)) return false;
        return createdAt.equals(todo.createdAt);
    }

    @Override
    public int hashCode() {
        int result = description == null ? 0 : description.hashCode();
        result = 31 * result + (isCompleted ? 1 : 0);
        result = 31 * result + createdAt.hashCode();
        return result;
    }
}