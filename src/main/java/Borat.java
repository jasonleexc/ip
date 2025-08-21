import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Borat {

    private static final List<Task> currList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Borat");
        System.out.println("What can I do for you?");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            command = sc.nextLine().trim();
            String[] words = command.split(" ");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listItems();
            } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                taskMarker(words);
            } else {
                currList.add(new Task(command));
                System.out.println("added: " + command);
            }
        }

        sc.close();
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