package vincentrk42.pushgo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;


public class Series implements Parcelable, Serializable{

    private static final String TAG = "SERIES CLASS: ";

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

//    public Series(String constructionString) {
//        if((constructionString.charAt(0) != '<') && (constructionString.charAt(constructionString.length()-1) != '>')) {
//            Log.d(TAG, "String not correct form: " + constructionString);
//            return;
//        }
//        constructionString = constructionString.substring(1,constructionString.length() - 1);
//        String[] data = constructionString.split("^");
//        if(data.length < 2)
//        {
//            Log.d(TAG, "String not correct form: " + constructionString);
//            return;
//        }
//        this.title = data[0];
//        this.description = data[1];
//        for(int i=2; i<data.length; i++)
//        {
//            sequence.add(new Event(data[i]));
//        }
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

    public ArrayList<Event> getEvents() { return sequence; }

    public void addEvent(String title, String description) {
        Event e = new Event(title, description);
        addEvent(e);
    }

    public void addEvent(String title, String description, int position) {
        Event e = new Event(title, description);
        addEvent(e, position);
    }

    public void addEvent(Event e)
    {
        sequence.add(e);
    }

    public void addEvent(Event e, int position)
    {
        sequence.add(position, e);
    }

    public void editEvent(Event e, int position)
    {
        sequence.remove(position);
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

    public int size()
    {
        return sequence.size();
    }

    public boolean equals(Series that)
    {
        if((!this.title.equals(that.title)) || (!this.description.equals(that.description)) || (this.sequence.size() != that.sequence.size()))
        {
            return false;
        }
        for(int i=0; i<this.sequence.size(); i++)
        {
            if(!this.sequence.get(i).equals(that.sequence.get(i)))
            {
                return false;
            }
        }
        return true;
    }

//    public String toString() {
//        String data = "<" + title + "^" + description + "^";
//        for(int i=0; i<sequence.size(); i++)
//        {
//            data += sequence.get(i).toString();
//            if(i < sequence.size() - 1) {
//                data += "^";
//            }
//        }
//        data +=">";
//        Log.d(TAG, "Series Data: " + data);
//        return data;
//    }

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
