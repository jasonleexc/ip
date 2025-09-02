package borat.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses raw user input strings into structured command arguments and
 * formats/validates date-time values.
 */
public class Parser {

    /**
     * Splits a raw command into the first word and the remaining description.
     *
     * @param command raw user input
     * @return a 2-element array: [commandWord, description]
     */
    public String[] parseCommand(String command) {
        String[] split = command.trim().split("\\s", 2);
        String firstWord = split[0];
        String description = split.length > 1 ? split[1] : "";

        return new String[] {firstWord, description};
    }

    /**
     * Parses a deadline description of the form "<desc> /by <d/M/yyyy HHmm>".
     *
     * @param description the user-provided description and due date
     * @return a 2-element array: [desc, formattedDeadline]
     * @throws IllegalArgumentException if required parts are missing or date invalid
     */
    public String[] parseDeadline(String description) {
        String[] parts = description.split("/by", 2);
        // handle exception
        if (parts.length != 2) {
            throw new IllegalArgumentException("Deadline command must include /by followed by date and time");
        }
        
        String desc = parts[0].trim();
        String dateTimeStr = parts[1].trim();
        
        try {
            LocalDateTime deadline = parseDateTime(dateTimeStr);
            String formattedDeadline = formatDateTime(deadline);
            return new String[] {desc, formattedDeadline};
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format. Use format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Parses an event description of the form
     * "<desc> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>".
     *
     * @param description the user-provided description and time range
     * @return a 3-element array: [desc, formattedStart, formattedEnd]
     * @throws IllegalArgumentException if required parts are missing or dates invalid
     */
    public String[] parseEvent(String description) {
        String[] parts = description.split("\\s*/from\\s*|\\s*/to\\s*", 3);
        // handle exception
        if (parts.length != 3) {
            throw new IllegalArgumentException("Event command must include /from and /to followed by date and time");
        }
        
        String desc = parts[0].trim();
        String startStr = parts[1].trim();
        String endStr = parts[2].trim();
        
        try {
            LocalDateTime start = parseDateTime(startStr);
            LocalDateTime end = parseDateTime(endStr);
            String formattedStart = formatDateTime(start);
            String formattedEnd = formatDateTime(end);
            return new String[] {desc, formattedStart, formattedEnd};
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format. Use format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Parses a date-time string using input pattern {@code d/M/yyyy HHmm}.
     *
     * @param dateTimeString input string
     * @return parsed {@link LocalDateTime}
     * @throws DateTimeParseException if the input does not match the pattern
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, inputFormatter);
    }

    /**
     * Formats a {@link LocalDateTime} to {@code MMM dd yyyy HH:mm}.
     *
     * @param dateTime date-time to format
     * @return formatted string
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(outputFormatter);
    }
}


