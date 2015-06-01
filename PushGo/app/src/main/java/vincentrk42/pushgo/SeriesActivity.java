package vincentrk42.pushgo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SeriesActivity extends ActionBarActivity {

    private static final String TAG = "SeriesActivity";

    private static final int NEW_EVENT_REQUEST_CODE = 2;
    private static final int EDIT_EVENT_REQUEST_CODE = 1;
    private static final int DELETE_SERIES_RESULT_CODE = 13;
    private static final int DELETE_EVENT_RESULT_CODE = 3;

    private final Context context = this;

    private Series serie;
    private EventAdapter eventAdapter;
    private Button addStepButton;
    private TextView numStepsTextView;
    private EditText serieTitleEditText;
    private Button saveButton;
    private Button deleteButton;

    private boolean newSerie;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ONCREATE CALLED FROM SERIESACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series_layout);

        newSerie = true;
        position = -1;

        numStepsTextView = (TextView) findViewById(R.id.numStepsTextView);
        serieTitleEditText = (EditText) findViewById(R.id.serieTitleEditText);



        if(getIntent().hasExtra("serie")) {
            serie = getIntent().getExtras().getParcelable("serie");
            newSerie = false;
        }
        if(getIntent().hasExtra("seriePosition"))
        {
            Log.d(TAG, "HAS POSITION");
            position = getIntent().getExtras().getInt("seriePosition");
        }
        if(!newSerie) {
            updateNumSteps();
            serieTitleEditText.setText(serie.getTitle());
        }
        else {
            numStepsTextView.setText("Steps: 0");
            serie = new Series();
        }

        ListView eventListView = (ListView) findViewById(R.id.eventListView);
        eventAdapter = new EventAdapter(this, serie.getEvents());
        eventListView.setAdapter(eventAdapter);

        addStepButton = (Button) findViewById(R.id.addStepButton);
        addStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, EventActivity.class);
                startActivityForResult(intent, NEW_EVENT_REQUEST_CODE);
            }
        });

        saveButton = (Button) findViewById(R.id.seriesSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serie.setTitle(serieTitleEditText.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("serie", serie);
                if(position >= 0) {
                    intent.putExtra("seriePosition", position);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        deleteButton = (Button) findViewById(R.id.sereisDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent intent = new Intent();
                               if(position >= 0) {
                                   intent.putExtra("seriePosition", position);
                               }
                               setResult(DELETE_SERIES_RESULT_CODE, intent);
                               finish();
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                           }
                       });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_EVENT_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                Event e = data.getExtras().getParcelable("event");
                if(serie == null)
                {
                    serie = new Series(serieTitleEditText.getText().toString(), "");
                }
                serie.addEvent(e);
            }
        }
        if(requestCode == EDIT_EVENT_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                int pos = data.getExtras().getInt("eventPosition");
                Event e = data.getExtras().getParcelable("event");
                serie.editEvent(e, pos);
            }
        }
        if(resultCode == DELETE_EVENT_RESULT_CODE) {
            int pos = data.getExtras().getInt("eventPosition");
            Log.d(TAG, "REMOVING EVENT : " + pos);
            serie.removeEvent(pos);
        }
        updateNumSteps();
        eventAdapter.notifyDataSetChanged();
    }

    private void updateNumSteps()
    {
        numStepsTextView.setText("Steps: " + serie.getEvents().size());
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
