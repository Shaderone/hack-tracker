package com.jdtech.nettracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Jess on 2016-05-27.
 */
public class RunnerFragment extends AppCompatActivity implements View.OnClickListener{


    Button subOne;
    Button subTwo;
    Button subFive;
    Button addOne;
    Button addTwo;
    Button addFive;
    Button subLink;
    Button subTag;
    Button addLink;
    Button addTag;

    TextView creditText;
    TextView linkText;
    TextView tagText;

    TextView oppCred;
    TextView oppTag;
    TextView oppLink;

    int credCounter = 5;
    int linkCounter = 0;
    int tagCounter = 0;

    int clickCount = 4;

    Chronometer runnerChrono;
    Button runnerStart;
    Button runnerEnd;
    Boolean timerStarted;
    long timeWhenStopped = 0;

    ClickLayout clickLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runner_fragment);

        subOne = (Button)findViewById(R.id.runnersubOne);
        subTwo = (Button)findViewById(R.id.runnersubTwo);
        subFive = (Button)findViewById(R.id.runnersubFive);
        addOne = (Button)findViewById(R.id.runneraddOne);
        addTwo = (Button)findViewById(R.id.runneraddTwo);
        addFive = (Button)findViewById(R.id.runneraddFive);
        creditText = (TextView)findViewById(R.id.runnercreditCounter);

        subTag = (Button)findViewById(R.id.runnersubTag);
        subLink = (Button)findViewById(R.id.runnersubLink);
        addTag = (Button)findViewById(R.id.runneraddTag);
        addLink = (Button)findViewById(R.id.runneraddLink);
        tagText = (TextView) findViewById(R.id.runnerTagCounter);
        linkText = (TextView) findViewById(R.id.runnerLinkCounter);

        oppCred = (TextView)findViewById(R.id.runneroppCredCounter);
        oppLink = (TextView)findViewById(R.id.runneroppLinkCounter);
        oppTag = (TextView)findViewById(R.id.runneroppTagCounter);

        runnerChrono = (Chronometer)findViewById(R.id.runnerChrono);
        runnerStart = (Button)findViewById(R.id.runnerStart);
        runnerEnd = (Button)findViewById(R.id.runnerEnd);
        
        subOne.setOnClickListener(this);
        subTwo.setOnClickListener(this);
        subFive.setOnClickListener(this);
        addOne.setOnClickListener(this);
        addTwo.setOnClickListener(this);
        addFive.setOnClickListener(this);

        subTag.setOnClickListener(this);
        addTag.setOnClickListener(this);

        subLink.setOnClickListener(this);
        addLink.setOnClickListener(this);

        creditText.setKeyListener(null);
        tagText.setKeyListener(null);
        linkText.setKeyListener(null);

        creditText.setText(Integer.toString(credCounter));
        tagText.setText(Integer.toString(tagCounter));
        linkText.setText(Integer.toString(linkCounter));

        oppCred.setText(Integer.toString(credCounter));
        oppTag.setText(Integer.toString(tagCounter));
        oppLink.setText(Integer.toString(linkCounter));

        runnerStart.setOnClickListener(this);
        runnerEnd.setOnClickListener(this);
        timerStarted = false;
        
        clickLayout = (ClickLayout)findViewById(R.id.runnerclickTrack);

        clickLayout.initialize(clickCount);

    }

    @Override
    public void onClick(View v) {
        if (v == subOne) {
            if (credCounter > 0) {
                credCounter--;
                creditText.setText(Integer.toString(credCounter));
                oppCred.setText(Integer.toString(credCounter));
            }
        }
        if (v == subTwo) {
            if (credCounter > 0) {
                if (credCounter <= 2) {
                    credCounter = 0;
                }
                else {
                    credCounter -= 2;
                }
                creditText.setText(Integer.toString(credCounter));
                oppCred.setText(Integer.toString(credCounter));
            }
        }

        if (v == subFive) {
            if (credCounter > 0) {
                if (credCounter <= 4) {
                    credCounter = 0;
                }
                else {
                    credCounter -= 4;
                }
                creditText.setText(Integer.toString(credCounter));
                oppCred.setText(Integer.toString(credCounter));
            }
        }
        if (v == addOne) {
            credCounter++;
            creditText.setText(Integer.toString(credCounter));
            oppCred.setText(Integer.toString(credCounter));
        }
        if (v == addTwo) {
            credCounter += 2;
            creditText.setText(Integer.toString(credCounter));
            oppCred.setText(Integer.toString(credCounter));
        }
        if (v == addFive) {
            credCounter += 4;
            creditText.setText(Integer.toString(credCounter));
            oppCred.setText(Integer.toString(credCounter));
        }
        if (v == subTag){
            if (tagCounter > 0) {
                tagCounter--;
                tagText.setText(Integer.toString(tagCounter));
                oppTag.setText(Integer.toString(tagCounter));
            }

        }
        if (v == addTag){
            tagCounter++;
            tagText.setText(Integer.toString(tagCounter));
            oppTag.setText(Integer.toString(tagCounter));
        }
        if (v == subLink){
            if (linkCounter > 0) {
                linkCounter--;
                linkText.setText(Integer.toString(linkCounter));
                oppLink.setText(Integer.toString(linkCounter));
            }
        }
        if (v == addLink){
            linkCounter++;
            linkText.setText(Integer.toString(linkCounter));
            oppLink.setText(Integer.toString(linkCounter));
        }
        if (v == runnerStart){
            if (timerStarted) {
                timeWhenStopped = runnerChrono.getBase() - SystemClock.elapsedRealtime();
                runnerChrono.stop();
                runnerStart.setText("Start");
                timerStarted = false;
            }
            else {
                runnerChrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                runnerChrono.start();
                runnerStart.setText("Pause");
                timerStarted = true;
            }
        }
        if (v == runnerEnd) {
            timeWhenStopped = runnerChrono.getBase() - SystemClock.elapsedRealtime();
            runnerChrono.stop();
            runnerStart.setText("Start");
            timerStarted = false;
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure you want to end the game?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            runnerChrono.setBase(SystemClock.elapsedRealtime());
                            timeWhenStopped = 0;
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            runnerChrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                            runnerChrono.start();
                            runnerStart.setText("Pause");
                            timerStarted = true;
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        timeWhenStopped = runnerChrono.getBase() - SystemClock.elapsedRealtime();
        runnerChrono.stop();
        runnerStart.setText("Start");
        timerStarted = false;
    }
}
