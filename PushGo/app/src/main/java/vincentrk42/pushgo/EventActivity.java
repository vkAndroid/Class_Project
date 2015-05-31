package vincentrk42.pushgo;

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
