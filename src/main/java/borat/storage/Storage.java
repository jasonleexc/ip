package borat.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import borat.task.Task;
import borat.task.ToDo;
import borat.task.Deadline;
import borat.task.Event;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // load existing tasks (empty list if file is missing)
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load: " + e.getMessage());
        }

        return tasks;
    }

    // save current tasks to file
    public void save(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            for (Task task : tasks) {
                lines.add(task.toFileString());
            }

            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    private Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }
            
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            
            Task task = null;
            
            if (taskType.equals("T")) {
                task = new ToDo(description);
            } else if (taskType.equals("D")) {
                if (parts.length >= 4) {
                    String deadline = parts[3];
                    task = new Deadline(description, deadline);
                }
            } else if (taskType.equals("E")) {
                if (parts.length >= 5) {
                    String start = parts[3];
                    String end = parts[4];
                    task = new Event(description, start, end);
                }
            }
            
            if (task != null && isDone) {
                task.setDone(true);
            }
            
            return task;
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            return null;
        }
    }
}


