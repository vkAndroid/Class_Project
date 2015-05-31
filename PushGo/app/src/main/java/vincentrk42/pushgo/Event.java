package vincentrk42.pushgo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 5/3/2015.
 */
public class Event implements Parcelable{
    private String title;
    private String description;
    // Time in milliseconds before ending this event
    private long timerTrigger;

    public Event(String description, String title, long timerTrigger) {
        this.description = description;
        this.title = title;
        this.timerTrigger = timerTrigger;
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

    // Parcelable starts here
    protected Event(Parcel in) {
        title = in.readString();
        description = in.readString();
        timerTrigger = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(timerTrigger);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    // Parcelable ends here
}
