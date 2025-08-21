public class Task {
    private final String description;
    private boolean isDone;

    Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
