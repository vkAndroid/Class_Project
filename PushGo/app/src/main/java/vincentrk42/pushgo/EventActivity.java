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

public class EventActivity extends ActionBarActivity {

    private static final String TAG = "EventActivity";
    private static final int DELETE_EVENT_RESULT_CODE = 3;

    private final Context context = this;

    private Event event;

    private EditText eventTitle;
    private EditText eventDescription;
    private Button saveButton;
    private Button deleteButton;

    private int position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on create called from event edit");
        setContentView(R.layout.event_layout);

        position = -1;

        eventTitle = (EditText) findViewById(R.id.eventTitleEditText);
        eventDescription = (EditText) findViewById(R.id.eventDescriptionEditText);

        if(getIntent().hasExtra("event")) {
            event = getIntent().getExtras().getParcelable("event");
        }
        if(getIntent().hasExtra("eventPosition")) {
            position = getIntent().getExtras().getInt("eventPosition");
        }

        if(event != null)
        {
            eventTitle.setText(event.getTitle());
            eventDescription.setText(event.getDescription());
        }

        saveButton = (Button) findViewById(R.id.eventSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = new Event(eventTitle.getText().toString(), eventDescription.getText().toString(), 0);
                Intent intent = new Intent();
                intent.putExtra("event", event);
                if(position >= 0) {
                    intent.putExtra("eventPosition", position);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        deleteButton = (Button) findViewById(R.id.eventDeleteButton);
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
                                    intent.putExtra("eventPosition", position);
                                }
                                setResult(DELETE_EVENT_RESULT_CODE, intent);
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
