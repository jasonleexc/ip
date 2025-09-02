package borat.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    @DisplayName("Test parsing simple command")
    void testParseSimpleCommand() {
        String[] result = parser.parseCommand("todo buy groceries");
        assertEquals("todo", result[0]);
        assertEquals("buy groceries", result[1]);
    }

    @Test
    @DisplayName("Test parsing command with single word")
    void testParseSingleWordCommand() {
        String[] result = parser.parseCommand("list");
        assertEquals("list", result[0]);
        assertEquals("", result[1]);
    }

    @Test
    @DisplayName("Test parsing command with empty string")
    void testParseEmptyCommand() {
        String[] result = parser.parseCommand("");
        assertEquals("", result[0]);
        assertEquals("", result[1]);
    }

    @Test
    @DisplayName("Test parsing command with whitespace")
    void testParseCommandWithWhitespace() {
        String[] result = parser.parseCommand("  deadline  return book  ");
        assertEquals("deadline", result[0]);
        assertEquals(" return book", result[1]);
    }

    @Test
    @DisplayName("Test parsing deadline command")
    void testParseDeadline() {
        String[] result = parser.parseDeadline("return book /by 2/12/2019 1800");
        assertEquals("return book", result[0]);
        assertEquals("Dec 02 2019 18:00", result[1]);
    }

    @Test
    @DisplayName("Test parsing deadline with extra whitespace")
    void testParseDeadlineWithWhitespace() {
        String[] result = parser.parseDeadline("  return book  /by  2/12/2019 1800  ");
        assertEquals("return book", result[0]);
        assertEquals("Dec 02 2019 18:00", result[1]);
    }

    @Test
    @DisplayName("Test parsing deadline with different date format")
    void testParseDeadlineDifferentFormat() {
        String[] result = parser.parseDeadline("submit report /by 15/10/2023 2359");
        assertEquals("submit report", result[0]);
        assertEquals("Oct 15 2023 23:59", result[1]);
    }

    @Test
    @DisplayName("Test parsing deadline without /by throws exception")
    void testParseDeadlineWithoutByThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseDeadline("return book");
        });
    }

    @Test
    @DisplayName("Test parsing deadline with invalid time format throws exception")
    void testParseDeadlineInvalidTimeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseDeadline("return book /by 2/12/2019 25:00");
        });
    }

    @Test
    @DisplayName("Test parsing event command")
    void testParseEvent() {
        String[] result = parser.parseEvent("team meeting /from 15/10/2019 1400 /to 15/10/2019 1600");
        assertEquals("team meeting", result[0]);
        assertEquals("Oct 15 2019 14:00", result[1]);
        assertEquals("Oct 15 2019 16:00", result[2]);
    }

    @Test
    @DisplayName("Test parsing event with extra whitespace")
    void testParseEventWithWhitespace() {
        String[] result = parser.parseEvent("  team meeting  /from  15/10/2019 1400  /to  15/10/2019 1600  ");
        assertEquals("team meeting", result[0]);
        assertEquals("Oct 15 2019 14:00", result[1]);
        assertEquals("Oct 15 2019 16:00", result[2]);
    }

    @Test
    @DisplayName("Test parsing event with different date format")
    void testParseEventDifferentFormat() {
        String[] result = parser.parseEvent("party /from 31/12/2023 2000 /to 01/01/2024 0200");
        assertEquals("party", result[0]);
        assertEquals("Dec 31 2023 20:00", result[1]);
        assertEquals("Jan 01 2024 02:00", result[2]);
    }

    @Test
    @DisplayName("Test parsing event without /from throws exception")
    void testParseEventWithoutFromThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseEvent("team meeting /to 15/10/2019 1600");
        });
    }

    @Test
    @DisplayName("Test parsing event without /to throws exception")
    void testParseEventWithoutToThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseEvent("team meeting /from 15/10/2019 1400");
        });
    }

    @Test
    @DisplayName("Test parsing event with invalid time format throws exception")
    void testParseEventInvalidTimeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parseEvent("team meeting /from 15/10/2019 25:00 /to 15/10/2019 26:00");
        });
    }
}
