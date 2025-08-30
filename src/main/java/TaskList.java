import java.util.ArrayList;
import java.util.List;

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

    private static void taskMarker(String[] words) {
        Task task = currList.get(Integer.parseInt(words[1]) - 1);

        if (words[0].equals("mark")) {
            task.setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            task.setDone(false);
            System.out.println("Ok, I've marked this task as not done yet: ");
        }

        System.out.println(" " + task.toString());
    }

    private static void listItems() {
        System.out.println("Here are the tasks in your list: ");
        if (currList.isEmpty()) {
            System.out.println("No items yet");
        } else {
            for (int i = 0; i < currList.size(); i++) {
                System.out.println(i + 1 + "." + currList.get(i));
            }
        }
    }

    // You will add methods here later, like add(), delete(), etc.
    public void addToDo(String description, UI ui) throws BoratExceptions {
        if (description.isEmpty()) {
            throw new BoratExceptions("Command cannot be empty!");
        }
        currList.add(new ToDo(description));
        System.out.println("added: " + description);
        System.out.println("Now you have " + currList.size() + " tasks in the list.");
        Storage.save(currList);
    }

    public void addEvent(String description, UI ui) {
        String[] lst = splitter(split, 3);
        currList.add(new Event(lst[0], lst[1], lst[2]));
        System.out.println("added: " + description);
        System.out.println("Now you have " + currList.size() + " tasks in the list.");
        Storage.save(currList);
    }

    public void addDeadline(String description, UI ui) {
        String[] lst = splitter(split, 2);
        currList.add(new Deadline(lst[0], lst[1]));
        System.out.println("added: " + description);
        System.out.println("Now you have " + currList.size() + " tasks in the list.");
        Storage.save(currList);
    }

    public void delete(String index, UI ui) throws BoratExceptions {
        if (description.isEmpty()) {
            throw new BoratExceptions("Please provide a task number to delete.");
        }
        deleteTask(Integer.parseInt(split[1]));
        System.out.println("Noted. I've removed this task: " + description);
        System.out.println("Now you have " + currList.size() + " tasks in the list.");
        Storage.save(currList);
    }

    private static void deleteTask(int num) {

        currList.remove(num - 1);
    }
}