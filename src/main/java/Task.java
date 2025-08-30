public abstract class Task {
    protected final String description;
    protected boolean isDone;

    Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    public abstract String getType();

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
