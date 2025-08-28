public class EventTask extends Task {
    private String startDate;
    private String endDate;

    public EventTask(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate + "to: " + endDate + ")";
    }
}
