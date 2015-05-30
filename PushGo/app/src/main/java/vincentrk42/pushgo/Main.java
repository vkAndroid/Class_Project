package vincentrk42.pushgo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Main extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Series> series = new ArrayList<Series>();
    private SeriesAdapter seriesAdapter;

    Button createNewSequenceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate is called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load series info from memory
        series.add(new Series("Title1", "Description1"));
        series.add(new Series("Title2", "Description2"));
        series.add(new Series("Title3", "Description3"));
        series.add(new Series("Title4", "Description4"));

        ListView seriesListView = (ListView) findViewById(R.id.seriesListView);
        seriesAdapter = new SeriesAdapter(this, series);
        seriesListView.setAdapter(seriesAdapter);



        createNewSequenceButton = (Button) findViewById(R.id.createNewSequenceButton);
        createNewSequenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "createNewSequenceButton clicked");

                //addSeries();
            }
        });

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

        // store series info to memory

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    private void addSeries()
//    {
//
//    }
//
//    private void removeSeries()
//    {
//
//    }
//
//    private void editSeries()
//    {
//
//    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
