package borat.task;

/**
 * An event spanning a start and end date/time.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Creates an event with description and time range.
     *
     * @param description task description
     * @param start formatted start time
     * @param end formatted end time
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

<<<<<<< HEAD
=======
    /**
     * Returns the formatted start time.
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the formatted end time.
     */
    public String getEnd() {
        return end;
    }

>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}


