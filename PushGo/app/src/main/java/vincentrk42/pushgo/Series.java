package vincentrk42.pushgo;

import java.util.ArrayList;

/**
 * Created by Vincent on 5/3/2015.
 */
public class Series {
    private ArrayList<Event> sequence;
    private String title;
    private String description;

    public Series() {
        title = "";
        description = "";
        sequence = new ArrayList<Event>();
    }

    public Series(String title, String description) {
        this.title = title;
        this.description = description;
        sequence = new ArrayList<Event>();
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

    public ArrayList<Event> getEvents() { return sequence; }

    public void addEvent(String title, String description) {
        Event e = new Event(title, description);
        sequence.add(e);
    }

    public void addEvent(String title, String description, int position) {
        Event e = new Event(title, description);
        sequence.add(position, e);
    }

    public void removeEvent(int position){
        sequence.remove(position);
    }

    public void moveEvent(int initialPosition, int finalPosition){
        Event e = sequence.get(initialPosition);
        sequence.remove(initialPosition);
        sequence.add(finalPosition, e);
    }

    public Event getEvent(int position) {
        return sequence.get(position);
    }







}
