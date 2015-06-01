package vincentrk42.pushgo;

import android.nfc.Tag;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;


public class Event implements Parcelable, Serializable{

    private static final String TAG = "EVENT CLASS: ";

    private String title;
    private String description;
    // Time in milliseconds before ending this event
    private long timerTrigger;

    public Event()
    {
        this.title = "";
        this.description = "";
        this.timerTrigger = -1;
    }

    public Event(String title, String description, long timerTrigger) {
        this.description = description;
        this.title = title;
        this.timerTrigger = timerTrigger;
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
        timerTrigger = -1;
    }

//    public Event(String constructionString)
//    {
//        if((constructionString.charAt(0) != '{') && (constructionString.charAt(constructionString.length()-1) != '}')) {
//            Log.d(TAG, "String not correct form: " + constructionString);
//            return;
//        }
//        constructionString = constructionString.substring(1,constructionString.length() - 1);
//        String[] data = constructionString.split("#");
//        if(data.length != 3)
//        {
//            Log.d(TAG, "String not correct form: " + constructionString);
//            return;
//        }
//        this.title = data[0];
//        this.description = data[1];
//        this.timerTrigger = Integer.getInteger(data[2], -1);
//    }

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

    public boolean equals(Event that)
    {
        return ((this.title.equals(that.title)) && (this.description.equals(that.description)) && (this.timerTrigger == that.timerTrigger));
    }

//    public String toString()
//    {
//        String data = "{" + title + "#" + description + "#" + timerTrigger + "}";
//        Log.d(TAG, "Event data: " + data);
//        return data;
//    }

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
