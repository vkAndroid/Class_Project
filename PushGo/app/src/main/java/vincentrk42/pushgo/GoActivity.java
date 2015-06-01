package vincentrk42.pushgo;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoActivity extends ActionBarActivity {

    private static final String TAG = "GoActivity";

    private Series serie;
    private int currentStep;

    private TextView seriesTitleTextView;
    private TextView currentStepNumberTextView;
    private TextView currentStepTitleTextView;
    private TextView stepDescriptionTextView;
    private TextView nextStepNumberTextView;
    private TextView nextStepTitleTextView;
    private Button skipButton;
    private Button stopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_layout);

        if(getIntent().hasExtra("serie")) {
            serie = getIntent().getExtras().getParcelable("serie");
        }

        currentStep = 0;
        instantiateTextViews();
        seriesTitleTextView.setText(serie.getTitle());
        updateTextViews();

        skipButton = (Button) findViewById(R.id.manualSkipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep++;
                if(currentStep >= serie.size()) {
                    finish();
                }
                updateTextViews();

            }
        });

        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void instantiateTextViews()
    {
        seriesTitleTextView = (TextView) findViewById(R.id.goSeriesTitleTextView);
        currentStepNumberTextView = (TextView) findViewById(R.id.currentStepNumberTextView);
        currentStepTitleTextView = (TextView) findViewById(R.id.currentStepTitleTextView);
        stepDescriptionTextView = (TextView) findViewById(R.id.stepDescriptionTextView);
        nextStepNumberTextView = (TextView) findViewById(R.id.nextStepNumberTextView);
        nextStepTitleTextView = (TextView) findViewById(R.id.nextStepTitleTextView);
    }

    private void updateTextViews()
    {
        if(currentStep < serie.size()) {
            currentStepNumberTextView.setText((currentStep + 1) + "");
            currentStepTitleTextView.setText(serie.getEvent(currentStep).getTitle());
            stepDescriptionTextView.setText(serie.getEvent(currentStep).getDescription());
            if (currentStep + 1 < serie.size()) {
                nextStepNumberTextView.setText((currentStep + 2) + "");
                nextStepTitleTextView.setText(serie.getEvent(currentStep + 1).getTitle());
            }
            else
            {
                nextStepNumberTextView.setText("");
                nextStepTitleTextView.setText("END");
            }
        }
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
