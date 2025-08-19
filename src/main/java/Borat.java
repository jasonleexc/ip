import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Borat {

    private static final List<String> currList = new ArrayList<>();

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
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equals("list")) {
                listItems();
            } else {
                currList.add(command);
                System.out.println("added: " + command);
            }
        }

        sc.close();
    }

    private static void listItems() {
        System.out.println(" ");
        if (currList.isEmpty()) {
            System.out.println("No items yet");
        } else {
            for (int i = 0; i < currList.size(); i++) {
                System.out.println(i + 1 + ". " + currList.get(i));
            }
        }
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}