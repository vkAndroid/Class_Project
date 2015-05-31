package vincentrk42.pushgo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class SeriesAdapter extends ArrayAdapter<Series> {

    private static final String TAG = "SeriesAdapter";
    private static final int EDIT_REQUEST_CODE = 11;


    private Context context;
    private ArrayList<Series> series;

    public SeriesAdapter(Context context, ArrayList<Series> series){
        super(context, R.layout.serie_layout, series);
        this.context = context;
        this.series = series;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.serie_layout, parent, false);

        TextView serieNameTextView = (TextView) view.findViewById(R.id.serieNameTextView);
        serieNameTextView.setText(series.get(position).getTitle());

        Button editSerieButton = (Button) view.findViewById(R.id.editSerieButton);
        editSerieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "edit button clicked: " + position);

                Intent intent = new Intent(getContext(), SeriesActivity.class);
                intent.putExtra("serie", series.get(position));
                intent.putExtra("seriePosition", position);
                ((Activity)context).startActivityForResult(intent, EDIT_REQUEST_CODE);


            }
        });

        Button serieGoButton = (Button) view.findViewById(R.id.serieGoButton);
        serieGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "GO button clicked: " + position);



            }
        });

        return view;
    }


}
