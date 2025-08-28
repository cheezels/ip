import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ChatBot {

    private TaskList tasks;

    public ChatBot() {
    }

    public String getIntroMessage() {
        return """
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?""";
    }

    public String getResponse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        switch (command) {
            case "list":
                return getTaskList();

            case "mark":
                return markTask(parts, true);

            case "unmark":
                return markTask(parts, false);

            case "todo":
                ToDoTask toDo = new ToDoTask(parts[1]);
                tasks.add(toDo);
                return "I shall add:\n" +
                        "  " + toDo + "\n" +
                        "Singapore needs you to complete your " + tasks.size() + " tasks";

            case "deadline":
                String[] deadlineParts = parts[1].split("/", 2);
                if (deadlineParts.length != 2) return "Discipline requires deadlines.";

                // Invalid date format check
                try {
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                    tasks.add(deadlineTask);

                    return "I shall add:\n" +
                            "  " + deadlineTask + "\n" +
                            "Singapore needs you to complete your " + tasks.size() + " tasks";

                } catch (DateTimeParseException e) {
                    return "Date must be in yyyy-mm-dd format.";
                }

            case "event":
                String[] eventParts = parts[1].split("/", 3);
                if (eventParts.length != 3) return "It takes time to build a country like Singapore.";

                EventTask eventTask = new EventTask(eventParts[0], eventParts[1], eventParts[2]);
                tasks.add(eventTask);

                return "I shall add:\n" +
                        "  " + eventTask + "\n" +
                        "Singapore needs you to complete your " + tasks.size() + " tasks";

            case "delete":
                try {
                    int taskNum = Integer.parseInt(parts[1]) - 1;

                    if (taskNum < 0) {
                        return "No negative numbers! Don't be stupid.";
                    }

                    if (taskNum >= tasks.size()) {
                        return "You overestimate your workload. Get to work!";
                    }

                    Task task = tasks.get(taskNum);
                    tasks.remove(taskNum);

                    return "I've spent my whole lifetime building this. Sadly you won't. Removing:\n" +
                           "  " + task + "\n" +
                           "Singapore urges you to do your remaining " + tasks.size() + " tasks";
                } catch (NumberFormatException e) {
                    return "You must specify a task number!";
                }
            case "save":
                try {
                    this.storage.save(this.tasks);
                    return "It has been written down.";
                } catch (IOException e) {
                    return "Error in saving file.";
                }
            default:
                return "BE CONCISE WITH YOUR WORDS.";
        }
    }

    public Command parseCommand(String input) throws Exception {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "mark":
                int i = getIndex(parts);
                return new MarkCommand(i, true);

            case "unmark":
                int j = getIndex(parts);
                return new MarkCommand(j, false);

            case "delete":
                int k = getIndex(parts);
                return new DeleteCommand(k);

            case "save":
                return new SaveCommand();

            case "todo":
                return new ToDoCommand(parts[1]);

            case "deadline":
                String[] deadlineParts = parts[1].split("/", 2);
                if (deadlineParts.length != 2) throw new Exception("Discipline requires deadlines.");

                return new DeadlineCommand(deadlineParts[0], deadlineParts[1]);

            case "event":
                String[] eventParts = parts[1].split("/", 3);
                if (eventParts.length != 3) throw new Exception("It takes time to build a country like Singapore.");

                return new EventCommand(eventParts[0], eventParts[1], eventParts[2]);

            default:
                throw new Exception("YOUR WORDS MEAN NOTHING!");
        }

    }

    public String getTaskList() {
        if (tasks.isEmpty()) {
            return """
                    This is not a game of cards!
                    Your list is empty, get to work!""";
        }

        StringBuilder sb = new StringBuilder("Your work awaits you!\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            char isDone = task.checkDone() ? 'X' : ' ';
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }
        return sb.toString().trim();
    }


    public int getIndex(String[] parts) throws Exception {
        if (parts.length < 2) {
            throw new Exception("You must specify a task number!");
        }

        int taskNum = Integer.parseInt(parts[1]) - 1;

        if (taskNum < 0) {
            throw new Exception ("No negative numbers! Don't be stupid.");
        }

        if (taskNum >= tasks.size()) {
            throw new Exception("You overestimate your workload. Get to work!");
        }
    }

    public String getEndMessage() {
        return """
                Goodbye fellow countryman! Majulah Singapura!""";
    }
}
