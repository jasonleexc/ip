import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private static final Path FILE = Paths.get(".", "data", "borat.txt");

    // load existing tasks (empty list if file is missing)
    public static List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.exists(FILE)) {
                List<String> lines = Files.readAllLines(FILE);
                for (Task task : tasks) {
                    lines.add(task.toFileString());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load: " + e.getMessage());
        }

        return tasks;
    }

    // save current tasks to file
    public static void save(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        try {
            Files.createDirectories(FILE.getParent());
            for (Task task : tasks) {
                lines.add(task.toFileString());
            }

            Files.write(FILE, lines);
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

}