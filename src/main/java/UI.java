import java.util.Scanner;

public class UI {

    private final Scanner in;

    public static final String WELCOMEMESSAGE = "Hello! I'm Borat.\nWhat can I do for you?";
    public static final GOODBYEMESSAGE = "Bye. Hope to see you again soon!";

    public UI() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    private static void greet() {
        System.out.println(WELCOMEMESSAGE);
    }

    private static void exit() {
        System.out.println(GOODBYEMESSAGE);
    }

    private static void showError(String msg) {
        System.out.println("Error: " + msg);
    }

}