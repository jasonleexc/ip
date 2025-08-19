import java.util.Scanner;

public class Borat {

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
            }

            System.out.println(command);
        }

        sc.close();
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}