package borat.ui;

import java.util.Scanner;

/**
 * Handles user interaction via standard input and output.
 */
public class UI {

    private final Scanner in;

<<<<<<< HEAD
    public static final String WELCOMEMESSAGE = "Hello! I'm Borat.\nWhat can I do for you?";
    public static final String GOODBYEMESSAGE = "Bye. Hope to see you again soon!";
=======
    /** Welcome message shown at startup. */
    public static final String WELCOME_MESSAGE = "Hello! I'm Borat.\nWhat can I do for you?";
    /** Goodbye message shown on exit. */
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4

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
<<<<<<< HEAD
        System.out.println(WELCOMEMESSAGE);
=======
        System.out.println(WELCOME_MESSAGE);
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
    }

    /**
     * Prints the goodbye message.
     */
<<<<<<< HEAD
    public void exit() {
        System.out.println(GOODBYEMESSAGE);
=======
    public String exit() {
        return GOODBYE_MESSAGE;
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
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


