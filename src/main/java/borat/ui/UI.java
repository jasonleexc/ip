package borat.ui;

import java.util.Scanner;

/**
 * Handles user interaction via standard input and output.
 */
public class UI {

    private final Scanner in;

    public static final String WELCOMEMESSAGE = "Hello! I'm Borat.\nWhat can I do for you?";
    public static final String GOODBYEMESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Creates a UI instance backed by a {@link Scanner} reading System.in.
     */
    public UI() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return raw command line
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void greet() {
        System.out.println(WELCOMEMESSAGE);
    }

    /**
     * Prints the goodbye message.
     */
    public void exit() {
        System.out.println(GOODBYEMESSAGE);
    }

    /**
     * Prints an error message.
     *
     * @param msg error description
     */
    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }

}


