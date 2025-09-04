package borat.task;

import java.util.ArrayList;
import java.util.List;

import borat.exception.BoratExceptions;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

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

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println("No items yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void addToDo(String description) throws BoratExceptions {
        if (description == null || description.isEmpty()) {
            throw new BoratExceptions("Command cannot be empty!");
        }
        tasks.add(new ToDo(description));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addEvent(String description, String start, String end) {
        tasks.add(new Event(description, start, end));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadline(String description, String deadline) {
        tasks.add(new Deadline(description, deadline));
        System.out.println("added: " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Finds tasks whose descriptions contain the given keyword and prints them.
     *
     * @param keyword keyword to search for (case-insensitive)
     */
    public void find(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        String lower = keyword == null ? "" : keyword.toLowerCase();
        int shown = 0;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lower)) {
                shown++;
                System.out.println(shown + "." + task.toString());
            }
        }
        if (shown == 0) {
            System.out.println("No matching tasks found");
        }
    }
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


