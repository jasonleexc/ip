public class Deadline extends Task {

    private final String end;
    private String type;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + end;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (by: " + end + ")";
    }
}