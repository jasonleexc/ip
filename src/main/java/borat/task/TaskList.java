package borat.task;

import java.util.ArrayList;
import java.util.List;

import borat.exception.BoratException;

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

    public String markTask(String[] words) {
        StringBuilder currString = new StringBuilder();
        try {
            int index = Integer.parseInt(words[1]) - 1;
            Task task = tasks.get(index);

            if (words[0].equals("mark")) {
                task.setDone(true);
                currString.append("Nice! I've marked this task as done:");
            } else {
                task.setDone(false);
                currString.append("Ok, I've marked this task as not done yet:");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number!");
        }

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
        StringBuilder currString = new StringBuilder();

        if (description == null || description.isEmpty()) {
            throw new BoratException("Command cannot be empty!");
        }
        tasks.add(new ToDo(description));
        currString.append("added: ").append(description).append(".\n");
        currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return currString.toString();
    }

    public String addEvent(String description, String start, String end) {
        StringBuilder currString = new StringBuilder();

        tasks.add(new Event(description, start, end));
        currString.append("added: ").append(description);
        currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return currString.toString();
    }

    public String addDeadline(String description, String deadline) {
        StringBuilder currString = new StringBuilder();

        tasks.add(new Deadline(description, deadline));
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
                currString.append(shown).append(".").append(task.toString());
            }
        }
        if (shown == 0) {
            currString.append("No matching tasks found");
        }

        return currString.toString();
    }
    public String delete(String index) throws BoratException {
        StringBuilder currString = new StringBuilder();

        try {
            int taskIndex = Integer.parseInt(index) - 1;
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            currString.append("Noted. I've removed this task:");
            currString.append(" ").append(deletedTask.toString()).append("\n");
            currString.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        } catch (NumberFormatException e) {
            throw new BoratException("Please provide a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            throw new BoratException("Task number does not exist.");
        }

        return currString.toString();
    }

}


