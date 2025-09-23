package borat.task;

public class Deadline extends Task {

    private final String end;

    /**
     * Creates a deadline task with a description and a due date/time.
     *
     * @param description task description
     * @param end formatted due date/time
     */
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

<<<<<<< HEAD
=======
    /**
     * Returns the formatted due date/time.
     */
    public String getEnd() {
        return end;
    }

>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
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


