package borat.task;

import java.util.ArrayList;
import java.util.List;

import borat.exception.BoratExceptions;

/**
 * Mutable collection of {@link Task} with helper operations used by commands.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Wraps an existing list of tasks.
     *
     * @param tasks initial tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying mutable list of tasks.
     *
     * @return task list
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks or unmarks a task at the given 1-based index, printing status.
     *
     * @param words array containing command and index.
     */
    public void markTask(String[] words) {
        try {
            int index = Integer.parseInt(words[1]) - 1;
            Task task = tasks.get(index);

            if (words[0].equals("mark")) {
                task.setDone(true);
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.setDone(false);
                System.out.println("Ok, I've marked this task as not done yet:");
            }

            System.out.println(" " + task.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number!");
        }
    }

    /**
     * Prints the task list to standard output.
     */
    public void listItems() {
        System.out.println("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println("No items yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Adds a ToDo task.
     *
     * @param description task description (must be non-empty)
     * @throws BoratExceptions if description is empty
     */
    public void addToDo(String description) throws BoratExceptions {
        if (description.isEmpty()) {
            throw new BoratExceptions("Command cannot be empty!");
        }
        tasks.add(new ToDo(description));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds an Event task.
     *
     * @param description description text
     * @param start formatted start time
     * @param end formatted end time
     */
    public void addEvent(String description, String start, String end) {
        tasks.add(new Event(description, start, end));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a Deadline task.
     *
     * @param description description text
     * @param deadline formatted due date/time
     */
    public void addDeadline(String description, String deadline) {
        tasks.add(new Deadline(description, deadline));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task by 1-based index.
     *
     * @param index index string from user input
     * @throws BoratExceptions if the index is invalid or out of bounds
     */
    public void delete(String index) throws BoratExceptions {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + deletedTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new BoratExceptions("Please provide a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            throw new BoratExceptions("Task number does not exist.");
        }
    }

}


