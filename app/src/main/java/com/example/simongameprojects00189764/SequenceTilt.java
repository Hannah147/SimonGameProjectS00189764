package com.example.simongameprojects00189764;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SequenceTilt extends AppCompatActivity implements SensorEventListener {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button btnBlue, btnYellow, btnGreen, btnRed;
    boolean blue = false, yellow = false, green = false, red = false, firstSequence = false, secondSequence = false, thirdSequence = false, fourthSequence = false, isFlat;
    int[] gameSequence = new int[120];
    int wrong = Color.parseColor("#800000");
    Integer userScore = 0;
    ConstraintLayout sequence_layout;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence_tilt);
        btnBlue = findViewById(R.id.btnBlue);
        btnYellow = findViewById(R.id.btnYellow);
        btnGreen = findViewById(R.id.btnGreen);
        btnRed = findViewById(R.id.btnRed);

        sequence_layout = findViewById(R.id.sequence_layout);


        gameSequence = getIntent().getIntArrayExtra("gameSequence");

        // we are going to use the sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    /*
     * When the app is brought to the foreground - using app on screen
     */
    protected void onResume() {
        super.onResume();
        Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        if(firstSequence = true) {
            userScore++;
        }

        else if(secondSequence = true && firstSequence == true) {
            userScore++;
        }

        else if(secondSequence = true && firstSequence == true && thirdSequence == true) {
            userScore++;
        }

        else if(secondSequence = true && firstSequence == true && thirdSequence == true && fourthSequence == true) {
            userScore++;
        }
        gameOver.putExtra("userScore", userScore);

    }

    /*
     * App running but not on screen - in the background
     */
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

            checkFirstSequence(x, y, z);

        if(firstSequence == true) {
            x = 0;
            y = 0;
            z = 0;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            mSensorManager.unregisterListener(this);
            checkSecondSequence(x, y, z);
        }

//        checkFlat(x, y, z);
//        if(firstSequence == true && isFlat == true) {
//            checkFlat(x, y, z);
//            checkSecondSequence(x, y, z);
//        }
//
//        if(firstSequence == true && secondSequence == true && thirdSequence == false && isFlat == true) {
//            checkFlat(x, y, z);
//            checkThirdSequence(x, y, z);
//        }
//
//        if(firstSequence == true && secondSequence == true && thirdSequence == true && fourthSequence == false && isFlat == true) {
//            checkFlat(x, y, z);
//            checkFourthSequence(x, y, z);
//        }
//
//        if(firstSequence == true && secondSequence == true && thirdSequence == true && fourthSequence == true && isFlat == true) {
//            checkFlat(x, y, z);
//            userScore = 4;
//        }

        gameOver.putExtra("userScore", userScore);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void setButtonVisibilty() {
        Handler h = new Handler();
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        sequence_layout.setBackgroundColor(wrong);
        btnRed.setVisibility(View.GONE);
        btnBlue.setVisibility(View.GONE);
        btnGreen.setVisibility(View.GONE);
        btnYellow.setVisibility(View.GONE);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(gameOver);
            }
        }, 1000);

    }

    public void checkFirstSequence(float x, float y, float z) {
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        // blue select
        if(x > 2 && z > 9 && gameSequence[0] == 1)
        {
            btnBlue.setPressed(true);
            firstSequence = true;
        }

        if(x > 2 && z > 9 && gameSequence[0] != 1)
        {
            btnBlue.setPressed(true);
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[0] == 4)
        {
            btnGreen.setPressed(true);
            firstSequence = true;
        }

        if(x < -5 && z < 7 && gameSequence[0] != 4)
        {
            btnGreen.setPressed(true);
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[0] == 3)
        {
            btnYellow.setPressed(true);
            firstSequence = true;
        }

        if(y < -4 && z < 7 && gameSequence[0] != 3)
        {
            btnYellow.setPressed(true);
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[0] == 2)
        {
            btnRed.setPressed(true);
            firstSequence = true;
        }

        if(y > 3 && z > 8 && gameSequence[0] != 2)
        {
            btnRed.setPressed(true);
            setButtonVisibilty();
        }

        if(firstSequence == true) {
            mSensorManager.unregisterListener(this);
        }

    }

    public void checkSecondSequence(float x, float y, float z) {
        x = 0;
        y = 0;
        z = 0;
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        // blue select
        if(x > 2 && z > 9 && gameSequence[1] == 1 && firstSequence == true)
        {
            btnBlue.setPressed(true);
            secondSequence = true;
        }

        if(x > 2 && z > 9 && gameSequence[1] != 1 && firstSequence == true)
        {
            btnBlue.setPressed(true);
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[1] == 4 && firstSequence == true)
        {
            btnGreen.setPressed(true);
            secondSequence = true;
        }

        if(x < -5 && z < 7 && gameSequence[1] != 4 && firstSequence == true)
        {
            btnGreen.setPressed(true);
            setButtonVisibilty();
        }

        // yellow select
        if(y < -4 && z < 7 && gameSequence[1] == 3 && firstSequence == true)
        {
            btnYellow.setPressed(true);
            secondSequence = true;
        }

        if(y < -4 && z < 7 && gameSequence[1] != 3 && firstSequence == true)
        {
            btnYellow.setPressed(true);
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[1] == 2 && firstSequence == true)
        {
            btnRed.setPressed(true);
            secondSequence = true;
        }

        if(y > 3 && z > 8 && gameSequence[1] != 2 && firstSequence == true)
        {
            btnRed.setPressed(true);
            setButtonVisibilty();
        }
    }

    public void checkThirdSequence(float x, float y, float z) {
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        // blue select
        if(x > 2 && z > 9 && gameSequence[2] == 1 && firstSequence == true && secondSequence == true)
        {
            btnBlue.setPressed(true);
            thirdSequence = true;
        }

        if(x > 2 && z > 9 && gameSequence[2] != 1 && firstSequence == true && secondSequence == true)
        {
            btnBlue.setPressed(true);
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[2] == 4 && firstSequence == true && secondSequence == true)
        {
            btnGreen.setPressed(true);
            thirdSequence = true;
        }

        if(x < -5 && z < 7 && gameSequence[2] != 4 && firstSequence == true && secondSequence == true)
        {
            btnGreen.setPressed(true);
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[2] == 3 && firstSequence == true && secondSequence == true)
        {
            btnYellow.setPressed(true);
            thirdSequence = true;
        }

        if(y < -4 && z < 7 && gameSequence[2] != 3 && firstSequence == true && secondSequence == true)
        {
            btnYellow.setPressed(true);
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[2] == 2 && firstSequence == true && secondSequence == true)
        {
            btnRed.setPressed(true);
            thirdSequence = true;
        }

        if(y > 3 && z > 8 && gameSequence[2] != 2 && firstSequence == true && secondSequence == true)
        {
            btnRed.setPressed(true);
            setButtonVisibilty();
        }

    }

    public void checkFourthSequence(float x, float y, float z) {
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        // blue select
        if(x > 2 && z > 9 && gameSequence[3] == 1 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnBlue.setPressed(true);
            fourthSequence = true;
        }

        if(x > 2 && z > 9 && gameSequence[3] != 1 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnBlue.setPressed(true);
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[3] == 4 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnGreen.setPressed(true);
            fourthSequence = true;
        }

        if(x < -5 && z < 7 && gameSequence[3] != 4 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnGreen.setPressed(true);
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[3] == 3 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnYellow.setPressed(true);
            fourthSequence = true;
        }

        if(y < -4 && z < 7 && gameSequence[3] != 3 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnYellow.setPressed(true);
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[3] == 2 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnRed.setPressed(true);
            thirdSequence = true;
        }

        if(y > 3 && z > 8 && gameSequence[3] != 2 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnRed.setPressed(true);
            setButtonVisibilty();
        }

    }

    public void checkFlat(float x, float y, float z) {
        if(x < 1 && y < 1 && z >  9) {
            isFlat = true;
            mSensorManager.unregisterListener(this);
        }

        if(isFlat = true) {
            btnRed.setPressed(false);
            btnBlue.setPressed(false);
            btnYellow.setPressed(false);
            btnGreen.setPressed(false);

        }
    }
}