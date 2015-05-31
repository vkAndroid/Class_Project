package vincentrk42.pushgo;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class SeriesActivity extends ActionBarActivity {

    private static final String TAG = "SeriesActivity";

    private Series serie;
    private EventAdapter eventAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ONCREATE CALLED FROM SERIESACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series_layout);

        serie = getIntent().getExtras().getParcelable("serie");

//        serie = new Series("Title", "Description");
//        serie.addEvent("EventTitle1", "Event Description 1");
//        serie.addEvent("EventTitle2", "Event Description 2");
//        serie.addEvent("EventTitle3", "Event Description 3");
//        serie.addEvent("EventTitle4", "Event Description 4");
//        serie.addEvent("EventTitle5", "Event Description 5");

        ListView eventListView = (ListView) findViewById(R.id.eventListView);
        eventAdapter = new EventAdapter(this, serie.getEvents());
        eventListView.setAdapter(eventAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
