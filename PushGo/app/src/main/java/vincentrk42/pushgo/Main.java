package vincentrk42.pushgo;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


public class Main extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final String DATA_SAVE_FILE_NAME = "data_save_file_name";
    private static final int NEW_SERIES_REQUEST_CODE = 12;
    private static final int EDIT_SERIES_REQUEST_CODE = 11;
    private static final int DELETE_SERIES_RESULT_CODE = 13;

    // set true to use fake data else set false
    private static final boolean USE_TEST_DATA = false;

    private ArrayList<Series> series;
    private SeriesAdapter seriesAdapter;

    Button createNewSequenceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate is called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        series = new ArrayList<Series>();

        if(USE_TEST_DATA)
        {
            // TEST DATA BEGIN
            for(int i=0; i<10; i++)
            {
                series.add(new Series("S-Title " + i, "S-Description " + i));
                for(int j=0; j<12; j++)
                {
                    series.get(i).addEvent("E-Title " + i + ", " + j, "E-Description " + i + ", " + j);
                }
            }
            // TEST DATA END
        }
        else {
            readData();
        }

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

    private void readData()
    {
        Log.d(TAG, "BEGIN READ DATA");
        try {
            File file = new File(Environment.getExternalStorageDirectory(), DATA_SAVE_FILE_NAME);
            FileInputStream inStream = new FileInputStream(file);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            int count = objectInStream.readInt();
            for(int i=0; i<count; i++)
            {
                series.add((Series) objectInStream.readObject());
            }
            objectInStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "END READ DATA");
        Log.d(TAG, "series size: " + series.size());


        // TODO READ IN DATA AND FORMAT IT CORRECTLY INTO THE PROPER CONTAINERS
//        try {
//                InputStream inStream = new FileInputStream(DATA_SAVE_FILE_NAME);
//                if(inStream != null) {
//                    Log.d(TAG, "FILE EXISTS!");
//                    InputStreamReader inputReader = new InputStreamReader(inStream);
//                    BufferedReader buffReader = new BufferedReader(inputReader);
//
//                    String data;
//                    do {
//                        data = buffReader.readLine();
//                        series.add(new Series(data));
//
//                    } while (data != null);
//                    buffReader.close();
//                }
//                else {
//                    Log.d(TAG, "NOT EXISTS, THE FILE DOES!");
//                }
//
//            } catch (java.io.IOException e) {
//                e.printStackTrace();
//            }
    }

    private void saveData()
    {
        Log.d(TAG, "BEGIN SAVE DATA");
        try {
            File file = new File(Environment.getExternalStorageDirectory(), DATA_SAVE_FILE_NAME);
            FileOutputStream outStream = new FileOutputStream(file);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeInt(series.size());
            for(Series s: series)
            {
                objectOutStream.writeObject(s);
            }
            objectOutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "END SAVE DATA");

        // TODO store series info to memory
//        File file = new File(DATA_SAVE_FILE_NAME);
//        try {
//            OutputStreamWriter outStream = new OutputStreamWriter(new FileOutputStream(file));
//            for(int i=0; i<series.size(); i++)
//            {
//                String data = series.get(i).toString() + "\n";
//                outStream.write(data);
//            }
//            outStream.close();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
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
        Log.d(TAG, "onPause called");
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
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
