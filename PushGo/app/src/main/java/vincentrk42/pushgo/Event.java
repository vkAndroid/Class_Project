package vincentrk42.pushgo;

/**
 * Created by Vincent on 5/3/2015.
 */
public class Event {
    private String title;
    private String description;
    // Time in milliseconds before ending this event
    private long timerTrigger;

    public Event(String description, String title, long timeTrigger) {
        this.description = description;
        this.title = title;
        this.timerTrigger = timeTrigger;
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
        timerTrigger = -1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimerTrigger() { return timerTrigger; }

    public void setTimerTrigger(long timerTrigger) { this.timerTrigger = timerTrigger; }
}
