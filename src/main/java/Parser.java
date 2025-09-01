

public class Parser {

    public String[] parseCommand(String command) {
        parts = command.nextLine().trim();
        String[] split = parts.split("\\s", 2);
        String firstWord = split[0];
        String description = split.length > 1 ? split[1] : "";

        if (firstWord.equals("deadline")) {
            String[] deadlineTask = parseDeadlineOrEvent(description, 2);
        } else if (firstWord.equals("event")) {
            String[] eventTask = parseDeadlineOrEvent(description, 3);
        }

        // for base ToDo task
        return new String[] {firstWord, description};
    }

    private static String[] parseDeadlineOrEvent(String cmd, int num) {

        if (num == 2) {
            String[] parts = cmd.split("/by", num);
            String desc = parts[0].trim();
            String deadline = parts[1].trim();

            return new String[] {desc, deadline};
        } else if (num == 3) {
            String[] parts = cmd.split("\\s*/from\\s*|\\s*/to\\s*", num);

            String desc = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();

            return new String[] {desc, start, end};
        }

        throw new IllegalArgumentException("num must be 2 or 3");

    }

}