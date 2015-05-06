package vincentrk42.pushgo;

/**
 * Created by Vincent on 5/3/2015.
 */
public class Event {
    private String title;
    private String description;
    // need some way to keep track of end triggers

    public Event(String description, String title) {
        this.description = description;
        this.title = title;
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


}
