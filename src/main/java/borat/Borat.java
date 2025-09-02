package borat;

import java.util.Scanner;

import borat.exception.BoratExceptions;
import borat.parser.Parser;
import borat.storage.Storage;
import borat.task.TaskList;
import borat.ui.UI;

/**
 * Entry point and main application loop for Borat.
 */
public class Borat {

    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a Borat instance using the provided storage file path.
     *
     * @param filePath path to the tasks data file used for load/save
     */
    public Borat(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        // load existing tasks from storage file
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Application entry point.
     *
     * @param args command line arguments (unused)
     * @throws BoratExceptions if startup fails
     */
    public static void main(String[] args) throws BoratExceptions {
        new Borat("data/tasks.txt").run();
    }

    /**
     * Runs the main REPL loop: reads commands, parses them, executes actions,
     * and persists the task list after each successful command.
     */
    private void run() {
        ui.greet();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String[] parsedCommand = parser.parseCommand(fullCommand);
                String firstWord = parsedCommand[0];
                String description = parsedCommand[1];

                if (firstWord.equals("bye")) {
                    ui.exit();
                    isExit = true;
                } else if (firstWord.equals("list")) {
                    tasks.listItems();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    String[] words = {firstWord, description};
                    tasks.markTask(words);
                } else if (firstWord.equals("todo")) {
                    tasks.addToDo(description);
                } else if (firstWord.equals("event")) {
                    try {
                        String[] eventParts = parser.parseEvent(description);
                        tasks.addEvent(eventParts[0], eventParts[1], eventParts[2]);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                } else if (firstWord.equals("deadline")) {
                    try {
                        String[] deadlineParts = parser.parseDeadline(description);
                        tasks.addDeadline(deadlineParts[0], deadlineParts[1]);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                } else if (firstWord.equals("delete")) {
                    tasks.delete(description);
                } else {
                   ui.showError("I don't know what that means.");
                }
                
                // save tasks once complete
                storage.save(tasks.getTasks());
            } catch (NumberFormatException e) {
                ui.showError("Please provide a valid number.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        sc.close();
    }
}


