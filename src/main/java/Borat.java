import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Borat {

    private UI Ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Borat(String filePath) {
        this.Ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            // load existing tasks from storage file
            this.tasks = new TaskList(storage.load());
        } catch (BoratExceptions e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws BoratExceptions {
        new Borat("data/tasks.txt").run();
    }

    private static void run() throws BoratExceptions {
        ui.greet();
        boolean isExit() = false;
        Scanner sc = new Scanner(System.in);
        String command;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String command = parser.parseCommand(fullCommand);

                if (command.equals("bye")) {
                    ui.exit();
                } else if (command.equals("list")) {
                    tasks.listItems();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    tasks.taskMarker(split);
                    Storage.save(currList);
                } else if (firstWord.equals("todo")) {
                    tasks.addToDo(description, ui);
                } else if (firstWord.equals("event")) {
                    tasks.addEvent(description, ui);
                } else if (firstWord.equals("deadline")) {
                    tasks.addDeadline(description, ui);
                } else if (firstWord.equals("delete")) {
                    tasks.delete(description, ui);
                } else {
                   ui.showError("I don't know what that means. ");
                }
            } catch (NumberFormatException e) {
                ui.showError("Please provide a valid number. ");
            }
        }

        sc.close();
    }
}