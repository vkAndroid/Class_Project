package vincentrk42.pushgo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class EventAdapter extends ArrayAdapter<Event> {

    private static final String TAG = "EventAdapter";

    private Context context;
    private ArrayList<Event> events;

    public EventAdapter(Context context, ArrayList<Event> events){
        super(context, R.layout.event_listview_layout, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.serie_layout, parent, false);

        TextView eventNumberAndTitle = (TextView) view.findViewById(R.id.eventNumberAndTitle);
        eventNumberAndTitle.setText((position+1) + " | " + events.get(position).getTitle());

        Button eventUpButton = (Button) view.findViewById(R.id.eventUpButton);
        eventUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "event up button clicked: " + position);


            }
        });

        Button eventDownButton = (Button) view.findViewById(R.id.eventDownButton);
        eventDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "event down button clicked: " + position);



            }
        });

        Button eventEditButton = (Button) view.findViewById(R.id.eventEditButton);
        eventEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "event EDIT button clicked: " + position);



            }
        });

        return view;
    }
}
