package vincentrk42.pushgo;

import android.content.Intent;
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
    private static final int NEW_SERIES_REQUEST_CODE = 12;
    private static final int EDIT_SERIES_REQUEST_CODE = 11;
    private static final int DELETE_SERIES_RESULT_CODE = 13;

    private ArrayList<Series> series;
    private SeriesAdapter seriesAdapter;

    Button createNewSequenceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate is called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        series = new ArrayList<Series>();

        // TEST DATA BEGIN
        for(int i=0; i<10; i++)
        {
            series.add(new Series("S-Title" + i, "S-Description" + i));
            for(int j=0; j<12; j++)
            {
                series.get(i).addEvent("E-Title" + i + ", " + j, "E-Description" + i + ", " + j);
            }
        }
        // TEST DATA END

        //load series info from memory


        ListView seriesListView = (ListView) findViewById(R.id.seriesListView);
        seriesAdapter = new SeriesAdapter(this, series);
        seriesListView.setAdapter(seriesAdapter);



        createNewSequenceButton = (Button) findViewById(R.id.createNewSequenceButton);
        createNewSequenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "createNewSequenceButton clicked");
                Intent intent = new Intent(Main.this, SeriesActivity.class);
                startActivityForResult(intent, NEW_SERIES_REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_SERIES_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                Series serie = data.getExtras().getParcelable("serie");
                addSerie(serie);
            }
        }
        if(requestCode == EDIT_SERIES_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                int pos = data.getExtras().getInt("seriePosition");
                Series serie = data.getExtras().getParcelable("serie");
                editSeries(pos, serie);
            }
        }
        if(resultCode == DELETE_SERIES_RESULT_CODE){
            int pos = data.getExtras().getInt("seriePosition");
            removeSeries(pos);
        }


        // TODO
        seriesAdapter.notifyDataSetChanged();
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

        // TODO store series info to memory

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addSerie(Series serie)
    {
        series.add(serie);
    }

    private void removeSeries(int position)
    {
        if(position <= series.size()) {
            series.remove(position);
        }
    }

    private void editSeries(int position, Series serie)
    {
        series.remove(position);
        series.add(position, serie);
    }





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



}
