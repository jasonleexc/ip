package borat.task;

public class Deadline extends Task {

    private final String end;

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
        return super.toString() + " (by: " + end + ")";
    }
}


