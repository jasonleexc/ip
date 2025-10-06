package borat.ui;

import java.util.Scanner;

/**
 * Handles user interaction via standard input and output.
 */
public class UI {

    private final Scanner in;

    /** Welcome message shown at startup. */
    public static final String WELCOME_MESSAGE = "Hello! I'm Borat.\nWhat can I do for you?";
    /** Goodbye message shown on exit. */
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public UI() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void exit() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }

}


