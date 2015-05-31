package vincentrk42.pushgo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Vincent on 5/3/2015.
 */
public class Series implements Parcelable{
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



    // Parcelable starts here
    protected Series(Parcel in) {
        if (in.readByte() == 0x01) {
            sequence = new ArrayList<Event>();
            in.readList(sequence, Event.class.getClassLoader());
        } else {
            sequence = null;
        }
        title = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (sequence == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(sequence);
        }
        dest.writeString(title);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Series> CREATOR = new Parcelable.Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };
    // Parcelable ends here

}
