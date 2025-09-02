package borat.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private ToDo todo;
    private Deadline deadline;
    private Event event;

    @BeforeEach
    void setUp() {
        todo = new ToDo("Test todo task");
        deadline = new Deadline("Test deadline task", "Dec 31 2023 23:59");
        event = new Event("Test event task", "Dec 31 2023 20:00", "Dec 31 2023 22:00");
    }

    @Test
    void testTaskCreation() {
        assertNotNull(todo);
        assertNotNull(deadline);
        assertNotNull(event);
        
        // Test that tasks are created with correct descriptions
        assertTrue(todo.toString().contains("Test todo task"));
        assertTrue(deadline.toString().contains("Test deadline task"));
        assertTrue(event.toString().contains("Test event task"));
    }

    @Test
    void testTaskInitialState() {
        // Test that tasks start as not done
        assertTrue(todo.toString().contains("[ ]"));
        assertTrue(deadline.toString().contains("[ ]"));
        assertTrue(event.toString().contains("[ ]"));
    }

    @Test
    void testSetDone() {
        todo.setDone(true);
        assertTrue(todo.toString().contains("[X]"));
        
        todo.setDone(false);
        assertTrue(todo.toString().contains("[ ]"));
    }

    @Test
    void testGetType() {
        assertEquals("T", todo.getType());
        assertEquals("D", deadline.getType());
        assertEquals("E", event.getType());
    }

    @Test
    void testToString() {
        // Test unmarked tasks
        assertEquals("[T][ ] Test todo task", todo.toString());
        assertEquals("[D][ ] Test deadline task (by: Dec 31 2023 23:59)", deadline.toString());
        assertEquals("[E][ ] Test event task (from: Dec 31 2023 20:00 to: Dec 31 2023 22:00)", event.toString());
        
        // Test marked tasks
        todo.setDone(true);
        deadline.setDone(true);
        event.setDone(true);
        
        assertEquals("[T][X] Test todo task", todo.toString());
        assertEquals("[D][X] Test deadline task (by: Dec 31 2023 23:59)", deadline.toString());
        assertEquals("[E][X] Test event task (from: Dec 31 2023 20:00 to: Dec 31 2023 22:00)", event.toString());
    }

    @Test
    void testToFileString() {
        // Test unmarked tasks
        assertEquals("T | 0 | Test todo task", todo.toFileString());
        assertEquals("D | 0 | Test deadline task | Dec 31 2023 23:59", deadline.toFileString());
        assertEquals("E | 0 | Test event task | Dec 31 2023 20:00 | Dec 31 2023 22:00", event.toFileString());
        
        // Test marked tasks
        todo.setDone(true);
        deadline.setDone(true);
        event.setDone(true);
        
        assertEquals("T | 1 | Test todo task", todo.toFileString());
        assertEquals("D | 1 | Test deadline task | Dec 31 2023 23:59", deadline.toFileString());
        assertEquals("E | 1 | Test event task | Dec 31 2023 20:00 | Dec 31 2023 22:00", event.toFileString());
    }

    @Test
    void testEmptyDescription() {
        ToDo emptyTodo = new ToDo("");
        assertEquals("[T][ ] ", emptyTodo.toString());
    }

    @Test
    void testSpecialCharactersInDescription() {
        ToDo specialTodo = new ToDo("Task with special chars: !@#$%^&*()");
        assertTrue(specialTodo.toString().contains("Task with special chars: !@#$%^&*()"));
    }

    @Test
    void testLongDescription() {
        String longDesc = "This is a very long description that contains many words and should be handled properly by the task system without any issues or truncation";
        ToDo longTodo = new ToDo(longDesc);
        assertTrue(longTodo.toString().contains(longDesc));
    }
}
