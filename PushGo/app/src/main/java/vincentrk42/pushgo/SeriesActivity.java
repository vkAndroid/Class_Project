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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.series_layout);
        Log.d(TAG, "ONCREATE CALLED FROM SERIESACTIVITY");

        serie = new Series("Title", "Description");
        serie.addEvent("EventTitle1", "Event Desctription 1");
        serie.addEvent("EventTitle2", "Event Desctription 2");
        serie.addEvent("EventTitle3", "Event Desctription 3");
        serie.addEvent("EventTitle4", "Event Desctription 4");
        serie.addEvent("EventTitle5", "Event Desctription 5");

        ListView eventListView = (ListView) findViewById(R.id.seriesListView);
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
