import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
