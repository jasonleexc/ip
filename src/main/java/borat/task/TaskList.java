package borat.task;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import borat.exception.BoratExceptions;

/**
 * Mutable collection of {@link Task} with helper operations used by commands.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import borat.exception.BoratException;

public class TaskList {
    private final List<Task> tasks;

>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

<<<<<<< HEAD
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
=======
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list parameter cannot be null";
        this.tasks = tasks;
    }

>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
    public List<Task> getTasks() {
        return tasks;
    }

<<<<<<< HEAD
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
=======
    public String markTask(String[] words) {
        assert words != null : "Words array cannot be null";
        assert words.length >= 2 : "Words array must have at least 2 elements";
        assert words[0] != null : "Command word cannot be null";
        assert words[1] != null : "Description cannot be null";
        
        StringBuilder currString = new StringBuilder();
        try {
            int index = Integer.parseInt(words[1]) - 1;
            assert index >= 0 : "Task index must be non-negative";
            assert index < tasks.size() : "Task index must be within bounds";
            
            Task task = tasks.get(index);
            assert task != null : "Retrieved task cannot be null";

            if (words[0].equals("mark")) {
                task.setDone(true);
                currString.append("Nice! I've marked this task as done:");
            } else {
                task.setDone(false);
                currString.append("Ok, I've marked this task as not done yet:");
            }
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number!");
        }
<<<<<<< HEAD
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
=======

        return currString.toString();
    }

    public String listTasks() {
        StringBuilder currString = new StringBuilder("Here are the tasks in your list:\n");
        if (tasks.isEmpty()) {
            currString.append("No items yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                currString.append(i + 1).append('.').append(tasks.get(i)).append('\n');
            }
        }

        return currString.toString();
    }

    public String addToDo(String description) throws BoratException {
        assert description != null : "Description cannot be null";
        
        StringBuilder currString = new StringBuilder();

        if (description.isEmpty()) {
            throw new BoratException("Command cannot be empty!");
        }
        
        int initialSize = tasks.size();
        tasks.add(new ToDo(description));
        assert tasks.size() == initialSize + 1 : "Task list size must increase by 1";
        
        currString.append("added: ").append(description).append(".\n");
        currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return currString.toString();
    }

    public String addEvent(String description, String start, String end) {
        assert description != null : "Event description cannot be null";
        assert start != null : "Event start time cannot be null";
        assert end != null : "Event end time cannot be null";
        
        StringBuilder currString = new StringBuilder();

        int initialSize = tasks.size();

        // detect clashes before adding event
        LocalDateTime startDate = parseDisplayDateTime(start);
        LocalDateTime endDate = parseDisplayDateTime(end);
        String clashWarning = detectClash(startDate, endDate);
        tasks.add(new Event(description, start, end));
        if (!clashWarning.isEmpty()) {
            currString.append("Take note: schedule clash detected: ").append(clashWarning).append("\n");
        }

        assert tasks.size() == initialSize + 1 : "Task list size must increase by 1";
        
        currString.append("added: ").append(description);
        currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return currString.toString();
    }

    public String addDeadline(String description, String deadline) {
        assert description != null : "Deadline description cannot be null";
        assert deadline != null : "Deadline time cannot be null";
        
        StringBuilder currString = new StringBuilder();

        int initialSize = tasks.size();

        // detect clashes with events (deadline occurring within an event window)
        LocalDateTime due = parseDisplayDateTime(deadline);
        String clashWarning = detectClash(due, due);
        tasks.add(new Deadline(description, deadline));
        if (!clashWarning.isEmpty()) {
            currString.append("Please note: deadline overlaps with another scheduled event: ").append(clashWarning).append("\n");
        }

        assert tasks.size() == initialSize + 1 : "Task list size must increase by 1";
        
        currString.append("added: ").append(description);
        currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return currString.toString();
    }

    /**
     * Finds tasks whose descriptions contain the given keyword and prints them.
     *
     * @param keyword keyword to search for (case-insensitive)
     */
    public String find(String keyword) {
        StringBuilder currString = new StringBuilder();

        currString.append("Here are the matching tasks in your list:");
        String lower = keyword == null ? "" : keyword.toLowerCase();
        int shown = 0;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lower)) {
                shown++;
                currString.append(shown).append(".").append(task);
            }
        }
        if (shown == 0) {
            currString.append("No matching tasks found");
        }

        return currString.toString();
    }
    public String delete(String index) throws BoratException {
        assert index != null : "Index string cannot be null";
        
        StringBuilder currString = new StringBuilder();

        try {
            int taskIndex = Integer.parseInt(index) - 1;
            assert taskIndex >= 0 : "Task index must be non-negative";
            assert taskIndex < tasks.size() : "Task index must be within bounds";
            
            int initialSize = tasks.size();
            Task deletedTask = tasks.get(taskIndex);
            assert deletedTask != null : "Task to delete cannot be null";
            
            tasks.remove(taskIndex);
            assert tasks.size() == initialSize - 1 : "Task list size must decrease by 1";
            
            currString.append("Noted. I've removed this task:");
            currString.append(" ").append(deletedTask).append("\n");
            currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        } catch (NumberFormatException e) {
            throw new BoratException("Please provide a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            throw new BoratException("Task number does not exist.");
        }

        return currString.toString();
    }

    private LocalDateTime parseDisplayDateTime(String display) {
        // parse formats using pattern "MMM dd yyyy HH:mm"; match that here
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return LocalDateTime.parse(display, outputFormatter);
    }

    private String detectClash(LocalDateTime newStart, LocalDateTime newEnd) {
        StringBuilder conflicts = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Event) {
                Event ev = (Event) task;
                try {
                    LocalDateTime s = parseDisplayDateTime(ev.getStart());
                    LocalDateTime e = parseDisplayDateTime(ev.getEnd());
                    if (intervalsOverlap(newStart, newEnd, s, e)) {
                        if (conflicts.length() > 0) {
                            conflicts.append(", ");
                        }
                        conflicts.append('"').append(ev.getDescription()).append('"');
                    }
                } catch (DateTimeParseException ignored) {
                    // skip unparseable existing entries
                }
            } else if (task instanceof Deadline) {
                Deadline dl = (Deadline) task;
                try {
                    LocalDateTime due = parseDisplayDateTime(dl.getEnd());
                    if (!newStart.isAfter(due) && !newEnd.isBefore(due)) {
                        if (conflicts.length() > 0) {
                            conflicts.append(", ");
                        }
                        conflicts.append('"').append(dl.getDescription()).append('"');
                    }
                } catch (DateTimeParseException ignored) {
                }
            }
        }
        return conflicts.toString();
    }

    private boolean intervalsOverlap(LocalDateTime aStart, LocalDateTime aEnd,
                                     LocalDateTime bStart, LocalDateTime bEnd) {
        return !aEnd.isBefore(bStart) && !aStart.isAfter(bEnd);
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
    }

}


