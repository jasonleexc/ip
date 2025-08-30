import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Borat {

    private static final List<Task> currList = Storage.load();

    public static void main(String[] args) throws BoratExceptions {
        greet();
        run();
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Borat");
        System.out.println("What can I do for you?");
    }

    private static void run() throws BoratExceptions {
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            try {
                command = sc.nextLine().trim();
                String[] split = command.split("\\s", 2);
                String firstWord = split[0];
                String description = split.length > 1 ? split[1] : "";

                // handle exceptions
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    listItems();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    taskMarker(split);
                    Storage.save(currList);
                } else if (firstWord.equals("todo")) {
                    if (description.isEmpty()) {
                        throw new BoratExceptions("Command cannot be empty!");
                    }
                    currList.add(new ToDo(description));
                    System.out.println("added: " + description);
                    System.out.println("Now you have " + currList.size() + " tasks in the list.");
                    Storage.save(currList);
                } else if (firstWord.equals("event")) {
                    String[] lst = splitter(split, 3);
                    currList.add(new Event(lst[0], lst[1], lst[2]));
                    System.out.println("added: " + description);
                    System.out.println("Now you have " + currList.size() + " tasks in the list.");
                    Storage.save(currList);
                } else if (firstWord.equals("deadline")) {
                    String[] lst = splitter(split, 2);
                    currList.add(new Deadline(lst[0], lst[1]));
                    System.out.println("added: " + description);
                    System.out.println("Now you have " + currList.size() + " tasks in the list.");
                    Storage.save(currList);
                } else if (firstWord.equals("delete")) {
                    if (description.isEmpty()) {
                        throw new BoratExceptions("Please provide a task number to delete.");
                    }
                    deleteTask(Integer.parseInt(split[1]));
                    System.out.println("Noted. I've removed this task: " + description);
                    System.out.println("Now you have " + currList.size() + " tasks in the list.");
                    Storage.save(currList);
                } else {
                    System.out.println("I don't know what that means.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number. ");
            }

        }

        sc.close();
    }

    private static void deleteTask(int num) {

        currList.remove(num - 1);
    }

    private static String[] splitter(String[] cmd, int num) {
        String rest = cmd[1];

        if (num == 2) {
            String[] parts = rest.split("/by", num);
            String desc = parts[0].trim();
            String deadline = parts[1].trim();

            return new String[] {desc, deadline};
        } else if (num == 3) {
            String[] parts = rest.split("\\s*/from\\s*|\\s*/to\\s*", num);

            String desc = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();

            return new String[] {desc, start, end};
        }

        throw new IllegalArgumentException("num must be 2 or 3");

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

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}