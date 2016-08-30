package com.jdtech.nettracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * Created by Jess on 2016-05-27.
 */
public class CorpFragment extends AppCompatActivity implements View.OnClickListener {


    // Initialize the buttons and other on-screen values
    Button subOne;
    Button subTwo;
    Button subFive;
    Button addOne;
    Button addTwo;
    Button addFive;
    Button subAgenda;
    Button subBadPub;
    Button addAgenda;
    Button addBadPub;

    TextView creditText;
    TextView agendaText;
    TextView badPubText;

    TextView oppCred;
    TextView oppBP;

    int credCounter = 5;
    int agendaCounter = 0;
    int badPubCounter = 0;

    int clickCount = 3;

    Chronometer corpChrono;
    Button corpStart;
    Button corpEnd;
    Boolean timerStarted;
    long timeWhenStopped = 0;

    ClickLayout clickLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corp_fragment);

        //Attach the variables to the corresponding id

        subOne = (Button)findViewById(R.id.subOne);
        subTwo = (Button)findViewById(R.id.subTwo);
        subFive = (Button)findViewById(R.id.subFive);
        addOne = (Button)findViewById(R.id.addOne);
        addTwo = (Button)findViewById(R.id.addTwo);
        addFive = (Button)findViewById(R.id.addFive);
        creditText = (TextView)findViewById(R.id.creditCounter);

        subAgenda = (Button)findViewById(R.id.subAgenda);
        subBadPub = (Button)findViewById(R.id.subBP);
        addAgenda = (Button)findViewById(R.id.addAgenda);
        addBadPub = (Button)findViewById(R.id.addBP);
        agendaText = (TextView) findViewById(R.id.agendaCounter);
        badPubText = (TextView) findViewById(R.id.badPubCounter);

        oppCred = (TextView)findViewById(R.id.oppCredCounter);
        oppBP = (TextView)findViewById(R.id.oppBPCounter);

        corpChrono = (Chronometer)findViewById(R.id.corpChrono);
        corpStart = (Button)findViewById(R.id.corpStart);
        corpEnd = (Button)findViewById(R.id.corpEnd);

        subOne.setOnClickListener(this);
        subTwo.setOnClickListener(this);
        subFive.setOnClickListener(this);
        addOne.setOnClickListener(this);
        addTwo.setOnClickListener(this);
        addFive.setOnClickListener(this);

        subAgenda.setOnClickListener(this);
        addAgenda.setOnClickListener(this);

        subBadPub.setOnClickListener(this);
        addBadPub.setOnClickListener(this);

        creditText.setKeyListener(null);
        agendaText.setKeyListener(null);
        badPubText.setKeyListener(null);

        creditText.setText(Integer.toString(credCounter));
        agendaText.setText(Integer.toString(agendaCounter));
        badPubText.setText(Integer.toString(badPubCounter));

        oppCred.setText(Integer.toString(credCounter));
        oppBP.setText(Integer.toString(badPubCounter));

        corpStart.setOnClickListener(this);
        corpEnd.setOnClickListener(this);
        timerStarted = false;


        clickLayout = (ClickLayout)findViewById(R.id.clickTrack);

        clickLayout.initialize(clickCount);


    }

    @Override
    public void onClick(View v) {

        //Branching checks to handle button presses
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
        if (v == subAgenda){
            if (agendaCounter > 0) {
                agendaCounter--;
                agendaText.setText(Integer.toString(agendaCounter));
            }

        }
        if (v == addAgenda){
            agendaCounter++;
            agendaText.setText(Integer.toString(agendaCounter));
        }
        if (v == subBadPub){
            if (badPubCounter > 0) {
                badPubCounter--;
                badPubText.setText(Integer.toString(badPubCounter));
                oppBP.setText(Integer.toString(badPubCounter));
            }
        }
        if (v == addBadPub){
            badPubCounter++;
            badPubText.setText(Integer.toString(badPubCounter));
            oppBP.setText(Integer.toString(badPubCounter));
        }
        if (v == corpStart){
            if (timerStarted) {
                timeWhenStopped = corpChrono.getBase() - SystemClock.elapsedRealtime();
                corpChrono.stop();
                corpStart.setText("Start");
                timerStarted = false;
            }
            else {
                corpChrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                corpChrono.start();
                corpStart.setText("Pause");
                timerStarted = true;
            }
        }
        if (v == corpEnd) {
            timeWhenStopped = corpChrono.getBase() - SystemClock.elapsedRealtime();
            corpChrono.stop();
            corpStart.setText("Start");
            timerStarted = false;
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure you want to end the game?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            corpChrono.setBase(SystemClock.elapsedRealtime());
                            timeWhenStopped = 0;
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            corpChrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                            corpChrono.start();
                            corpStart.setText("Pause");
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
        timeWhenStopped = corpChrono.getBase() - SystemClock.elapsedRealtime();
        corpChrono.stop();
        corpStart.setText("Start");
        timerStarted = false;
    }
}
