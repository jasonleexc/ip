package borat.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
<<<<<<< HEAD
import borat.exception.BoratExceptions;
=======
import borat.exception.BoratException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("Test empty TaskList creation")
    void testEmptyTaskListCreation() {
        assertNotNull(taskList);
        assertEquals(0, taskList.getTasks().size());
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test TaskList with existing tasks")
<<<<<<< HEAD
    void testTaskListWithExistingTasks() throws BoratExceptions {
=======
    void testTaskListWithExistingTasks() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        TaskList existingTaskList = new TaskList();
        existingTaskList.addToDo("Existing task");
        
        TaskList newTaskList = new TaskList(existingTaskList.getTasks());
        assertEquals(1, newTaskList.getTasks().size());
        assertFalse(newTaskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test adding ToDo task")
<<<<<<< HEAD
    void testAddToDo() throws BoratExceptions {
=======
    void testAddToDo() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Test todo");
        assertEquals(1, taskList.getTasks().size());
        assertFalse(taskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test adding Deadline task")
    void testAddDeadline() {
        taskList.addDeadline("Test deadline", "Dec 31 2023 23:59");
        assertEquals(1, taskList.getTasks().size());
        assertFalse(taskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test adding Event task")
    void testAddEvent() {
        taskList.addEvent("Test event", "Dec 31 2023 20:00", "Dec 31 2023 22:00");
        assertEquals(1, taskList.getTasks().size());
        assertFalse(taskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test adding empty ToDo description throws exception")
    void testAddEmptyToDoThrowsException() {
<<<<<<< HEAD
        assertThrows(BoratExceptions.class, () -> {
=======
        assertThrows(BoratException.class, () -> {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
            taskList.addToDo("");
        });
    }

    @Test
    @DisplayName("Test marking task as done")
<<<<<<< HEAD
    void testMarkTask() throws BoratExceptions {
=======
    void testMarkTask() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Test task");
        String[] markCommand = {"mark", "1"};
        
        // capture System.out to test output
        taskList.markTask(markCommand);
        
        // verify task is marked as done
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    @DisplayName("Test unmarking task")
<<<<<<< HEAD
    void testUnmarkTask() throws BoratExceptions {
=======
    void testUnmarkTask() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Test task");
        String[] unmarkCommand = {"unmark", "1"};
        
        taskList.markTask(unmarkCommand);
        
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    @DisplayName("Test marking invalid task number")
    void testMarkInvalidTaskNumber() {
        String[] invalidCommand = {"mark", "999"};
        
        // should just print error message
        assertDoesNotThrow(() -> {
            taskList.markTask(invalidCommand);
        });
    }

    @Test
    @DisplayName("Test marking task with non-numeric index")
    void testMarkNonNumericIndex() {
        String[] invalidCommand = {"mark", "abc"};
        
        assertDoesNotThrow(() -> {
            taskList.markTask(invalidCommand);
        });
    }

    @Test
    @DisplayName("Test deleting task")
<<<<<<< HEAD
    void testDeleteTask() throws BoratExceptions {
=======
    void testDeleteTask() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Task to delete");
        assertEquals(1, taskList.getTasks().size());
        
        taskList.delete("1");
        assertEquals(0, taskList.getTasks().size());
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test deleting task with invalid number")
    void testDeleteInvalidTaskNumber() {
<<<<<<< HEAD
        assertThrows(BoratExceptions.class, () -> {
=======
        assertThrows(BoratException.class, () -> {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
            taskList.delete("999");
        });
    }

    @Test
    @DisplayName("Test deleting task with non-numeric index")
    void testDeleteNonNumericIndex() {
<<<<<<< HEAD
        assertThrows(BoratExceptions.class, () -> {
=======
        assertThrows(BoratException.class, () -> {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
            taskList.delete("abc");
        });
    }

    @Test
    @DisplayName("Test deleting from empty list")
    void testDeleteFromEmptyList() {
<<<<<<< HEAD
        assertThrows(BoratExceptions.class, () -> {
=======
        assertThrows(BoratException.class, () -> {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
            taskList.delete("1");
        });
    }

    @Test
    @DisplayName("Test listing items when empty")
<<<<<<< HEAD
    void testListItemsEmpty() {
        // should just print "No items yet"
        assertDoesNotThrow(() -> {
            taskList.listItems();
=======
    void testListTasksEmpty() {
        // should just print "No items yet"
        assertDoesNotThrow(() -> {
            taskList.listTasks();
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        });
    }

    @Test
    @DisplayName("Test listing items with tasks")
<<<<<<< HEAD
    void testListItemsWithTasks() throws BoratExceptions {
=======
    void testListTasksWithTasks() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Task 1");
        taskList.addToDo("Task 2");
        
        // should not throw exception
        assertDoesNotThrow(() -> {
<<<<<<< HEAD
            taskList.listItems();
=======
            taskList.listTasks();
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        });
    }

    @Test
    @DisplayName("Test getTasks returns correct list")
<<<<<<< HEAD
    void testGetTasks() throws BoratExceptions {
=======
    void testGetTasks() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        taskList.addToDo("Task 1");
        taskList.addToDo("Task 2");
        
        assertEquals(2, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0).toString().contains("Task 1"));
        assertTrue(taskList.getTasks().get(1).toString().contains("Task 2"));
    }

    @Test
    @DisplayName("Test getTasks size changes")
<<<<<<< HEAD
    void testGetTasksSizeChanges() throws BoratExceptions {
=======
    void testGetTasksSizeChanges() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        assertEquals(0, taskList.getTasks().size());
        
        taskList.addToDo("Task 1");
        assertEquals(1, taskList.getTasks().size());
        
        taskList.addToDo("Task 2");
        assertEquals(2, taskList.getTasks().size());
    }

    @Test
    @DisplayName("Test getTasks isEmpty changes")
<<<<<<< HEAD
    void testGetTasksIsEmptyChanges() throws BoratExceptions {
=======
    void testGetTasksIsEmptyChanges() throws BoratException {
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        assertTrue(taskList.getTasks().isEmpty());
        
        taskList.addToDo("Task 1");
        assertFalse(taskList.getTasks().isEmpty());
        
        taskList.delete("1");
        assertTrue(taskList.getTasks().isEmpty());
    }
<<<<<<< HEAD
=======

    @Test
    @DisplayName("Test find prints matching tasks")
    void testFindWithMatches() throws BoratException {
        taskList.addToDo("read book");
        taskList.addDeadline("return book", "June 6th");
        taskList.addToDo("write report");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            taskList.find("book");
        } finally {
            System.setOut(original);
        }

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Here are the matching tasks in your list:"));
        assertTrue(output.contains("read book"));
        assertTrue(output.contains("return book"));
    }

    @Test
    @DisplayName("Test find prints no matches message")
    void testFindNoMatches() throws BoratException {
        taskList.addToDo("read book");
        taskList.addToDo("write report");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            taskList.find("xyz");
        } finally {
            System.setOut(original);
        }

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("No matching tasks found"));
    }
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
}
