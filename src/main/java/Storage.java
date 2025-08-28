import org.w3c.dom.ls.LSException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.isEmpty()) {
                tasks.add(parseTask(line));
            }
        }

        sc.close();
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            fw.write(formatTask(tasks.get(i)));
        }

        fw.close();
    }

    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDetails = parts[2];

        if (taskType.equals("T")) {
            ToDoTask toDoTask = new ToDoTask(taskDetails);
            if (isDone) {
                toDoTask.markDone();
            }
            return toDoTask;
        } else if (taskType.equals("D")) {
            DeadlineTask deadlineTask = new DeadlineTask(taskDetails, parts[3]);
            if (isDone) {
                deadlineTask.markDone();
            }
            return deadlineTask;
        } else if (taskType.equals("E")) {
            EventTask eventTask = new EventTask(taskDetails, parts[3], parts[4]);
            if (isDone) {
                eventTask.markDone();
            }
            return eventTask;
        } else {
            throw new IllegalArgumentException("Unknown Task Type: " + taskType);
        }
    }

    private String formatTask(Task task) {
        String isDone = task.checkDone() ? "1" : "0";

        if (task instanceof ToDoTask) {
            return "T|" + isDone + "|" + task.getDescription() + "\n";
        } else if (task instanceof DeadlineTask d) {
            return "D|" + isDone + "|" + d.getDescription() + "|" + d.getDeadline() + "\n";
        } else if (task instanceof EventTask e) {
            return "E|" + isDone + "|" + e.getDescription()
                    + "|" + e.getStartDate() + "|" + e.getEndDate() + "\n";
        } else {
            return "\n";
        }
    }


}
