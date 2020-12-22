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

    TextView tvTest, tvTest2, tvTest3, tvTest4, tvResult;
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
        tvTest = findViewById(R.id.tvTest);
        tvTest2 = findViewById(R.id.tvTest2);
        tvTest3 = findViewById(R.id.tvTest3);
        tvTest4 = findViewById(R.id.tvTest4);
        tvResult = findViewById(R.id.tvResult);

        btnBlue = findViewById(R.id.btnBlue);
        btnYellow = findViewById(R.id.btnYellow);
        btnGreen = findViewById(R.id.btnGreen);
        btnRed = findViewById(R.id.btnRed);

        sequence_layout = findViewById(R.id.sequence_layout);


        gameSequence = getIntent().getIntArrayExtra("gameSequence");

        int first;
        first = gameSequence[0];
        tvTest.setText(String.valueOf(first));

        int second;
        second = gameSequence[1];
        tvTest2.setText(String.valueOf(second));

        int third;
        third = gameSequence[2];
        tvTest3.setText(String.valueOf(third));

        int fourth;
        fourth = gameSequence[3];
        tvTest4.setText(String.valueOf(fourth));

//        Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);
//
//        if(firstSequence == true) {
//            tvResult.setText("Correct!");
//        }
//        else {
//            startActivity(gameOver);
//        }

//        userScore = userScore + 1;

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

//        if(firstSequence = true) {
//            userScore++;
//        }
//
//        if(secondSequence = true) {
//            userScore++;
//        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

//        if(firstSequence == false) {
            checkFirstSequence(x, y, z);
//            mSensorManager.unregisterListener(this);
//        }

        tvResult.setText(String.valueOf(firstSequence));
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


    public void greenClick(View view) {
//        green = true;
//        checkFirstSequence();
//        green = false;
//        checkSecondSequence();
    }

    public void blueClick(View view) {
//        blue = true;
//        checkFirstSequence();
//        blue = false;
//        checkSecondSequence();
    }

    public void redClick(View view) {
//        red = true;
//        checkFirstSequence();
//        red = false;
//        checkSecondSequence();
    }

    public void yellowClick(View view) {
//        yellow = true;
//        checkFirstSequence();
//        yellow = false;
//        checkSecondSequence();
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

//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

        // blue select
        if(x > 2 && z > 9 && gameSequence[0] == 1)
        {
            btnBlue.setPressed(true);
//            tvResult.setText("blue");
            firstSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x > 2 && z > 9 && gameSequence[0] != 1)
        {
            btnBlue.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[0] == 4)
        {
            btnGreen.setPressed(true);
//            tvResult.setText("green");
            firstSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x < -5 && z < 7 && gameSequence[0] != 4)
        {
            btnGreen.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[0] == 3)
        {
            btnYellow.setPressed(true);
//            tvResult.setText("yellow");
            firstSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y < -4 && z < 7 && gameSequence[0] != 3)
        {
            btnYellow.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[0] == 2)
        {
            btnRed.setPressed(true);
//            tvResult.setText("yellow");
            firstSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y > 3 && z > 8 && gameSequence[0] != 2)
        {
            btnRed.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

//        x = 0;
//        y = 0;
//        z = 0;

        if(firstSequence == true) {
            mSensorManager.unregisterListener(this);
        }

    }

    public void checkSecondSequence(float x, float y, float z) {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();



//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

        // blue select
        if(x > 2 && z > 9 && gameSequence[1] == 1 && firstSequence == true)
        {
            btnBlue.setPressed(true);
//            tvResult.setText("blue");
            secondSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x > 2 && z > 9 && gameSequence[1] != 1 && firstSequence == true)
        {
            btnBlue.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[1] == 4 && firstSequence == true)
        {
            btnGreen.setPressed(true);
//            tvResult.setText("green");
            secondSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x < -5 && z < 7 && gameSequence[1] != 4 && firstSequence == true)
        {
            btnGreen.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[1] == 3 && firstSequence == true)
        {
            btnYellow.setPressed(true);
//            tvResult.setText("yellow");
            secondSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y < -4 && z < 7 && gameSequence[1] != 3 && firstSequence == true)
        {
            btnYellow.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[1] == 2 && firstSequence == true)
        {
            btnRed.setPressed(true);
//            tvResult.setText("yellow");
            secondSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y > 3 && z > 8 && gameSequence[1] != 2 && firstSequence == true)
        {
            btnRed.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

    }

    public void checkThirdSequence(float x, float y, float z) {
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

        // blue select
        if(x > 2 && z > 9 && gameSequence[2] == 1 && firstSequence == true && secondSequence == true)
        {
            btnBlue.setPressed(true);
//            tvResult.setText("blue");
            thirdSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x > 2 && z > 9 && gameSequence[2] != 1 && firstSequence == true && secondSequence == true)
        {
            btnBlue.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[2] == 4 && firstSequence == true && secondSequence == true)
        {
            btnGreen.setPressed(true);
//            tvResult.setText("green");
            thirdSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x < -5 && z < 7 && gameSequence[2] != 4 && firstSequence == true && secondSequence == true)
        {
            btnGreen.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[2] == 3 && firstSequence == true && secondSequence == true)
        {
            btnYellow.setPressed(true);
//            tvResult.setText("yellow");
            thirdSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y < -4 && z < 7 && gameSequence[2] != 3 && firstSequence == true && secondSequence == true)
        {
            btnYellow.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[2] == 2 && firstSequence == true && secondSequence == true)
        {
            btnRed.setPressed(true);
//            tvResult.setText("yellow");
            thirdSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y > 3 && z > 8 && gameSequence[2] != 2 && firstSequence == true && secondSequence == true)
        {
            btnRed.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

    }

    public void checkFourthSequence(float x, float y, float z) {
        final Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);

        Handler h = new Handler();

//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

        // blue select
        if(x > 2 && z > 9 && gameSequence[3] == 1 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnBlue.setPressed(true);
//            tvResult.setText("blue");
            fourthSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x > 2 && z > 9 && gameSequence[3] != 1 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnBlue.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // green select
        if(x < -5 && z < 7 && gameSequence[3] == 4 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnGreen.setPressed(true);
//            tvResult.setText("green");
            fourthSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(x < -5 && z < 7 && gameSequence[3] != 4 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnGreen.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }


        // yellow select
        if(y < -4 && z < 7 && gameSequence[3] == 3 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnYellow.setPressed(true);
//            tvResult.setText("yellow");
            fourthSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y < -4 && z < 7 && gameSequence[3] != 3 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnYellow.setPressed(true);
            tvResult.setText("wrong");
            setButtonVisibilty();
        }

        // red select
        if(y > 3 && z > 8 && gameSequence[3] == 2 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnRed.setPressed(true);
//            tvResult.setText("yellow");
            thirdSequence = true;
            tvResult.setText(String.valueOf(userScore));
        }

        if(y > 3 && z > 8 && gameSequence[3] != 2 && firstSequence == true && secondSequence == true && thirdSequence == true)
        {
            btnRed.setPressed(true);
            tvResult.setText("wrong");
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


//    public void checkSequences(View view) {
//        Intent gameOver = new Intent(view.getContext(), GameOver.class);
//
//        checkFirstSequence();
//
//        red = false;
//        blue = false;
//        green = false;
//        yellow = false;
//        checkSecondSequence();
//
////            checkSecondSequence();
//        if(firstSequence == true) {
//            checkSecondSequence();
//        }
////        gameOver.putExtra("usersScore", userScore);
//    }
//
//    public void checkFirstSequence() {
//        Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);
//
//        if(blue == true && gameSequence[0] != 1)
//        {
//            firstSequence = false;
//            startActivity(gameOver);
//        }
//        else if(red == true && gameSequence[0] != 2)
//        {
//            firstSequence = false;
//            startActivity(gameOver);
//        }
//        else if(yellow == true && gameSequence[0] != 3)
//        {
//            firstSequence = false;
//            startActivity(gameOver);
//        }
//
//        else if(green == true && gameSequence[0] != 4)
//        {
//            firstSequence = false;
//            startActivity(gameOver);
//        }
//
//
//        else if(blue == true && gameSequence[0] == 1)
//        {
//            firstSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct!");
//        }
//        else if(red == true && gameSequence[0] == 2)
//        {
//            firstSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct!");
//        }
//        else if(yellow == true && gameSequence[0] == 3)
//        {
//            firstSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct!");
//        }
//
//        else if(green == true && gameSequence[0] == 4)
//        {
//            firstSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct!");
//        }
//
//            red = false;
//            blue = false;
//            green = false;
//            yellow = false;
//            firstSequence = true;
//            for (int i = 0; i< gameSequence.length; i++) {
//                gameSequence[i] = 0;
//            }
//            userScore = userScore + 1;
////            checkSecondSequence();
//
//    }
//
//    public void checkSecondSequence() {
//        Intent gameOver = new Intent(SequenceTilt.this, GameOver.class);
//
//        firstSequence = true;
//        red = false;
//        blue = false;
//        green = false;
//        yellow = false;
//        secondSequence = false;
//
//        if(blue == true && gameSequence[1] != 1)
//        {
//            secondSequence = false;
//            startActivity(gameOver);
//        }
//        else if(red == true && gameSequence[1] != 2)
//        {
//            secondSequence = false;
//            startActivity(gameOver);
//        }
//        else if(yellow == true && gameSequence[1] != 3)
//        {
//            secondSequence = false;
//            startActivity(gameOver);
//        }
//
//        else if(green == true && gameSequence[1] != 4)
//        {
//            secondSequence = false;
//            startActivity(gameOver);
//        }
//
//
//        else if(blue == true && gameSequence[1] == 1)
//        {
//            secondSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct Again!");
//        }
//        else if(red == true && gameSequence[1] == 2)
//        {
//            secondSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct Again!");
//        }
//        else if(yellow == true && gameSequence[1] == 3)
//        {
//            secondSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct Again!");
//        }
//
//        else if(green == true && gameSequence[1] == 4)
//        {
//            secondSequence = true;
//            userScore = userScore + 1;
//            tvResult.setText("Correct Again!");
//        }
//
//            red = false;
//            blue = false;
//            green = false;
//            yellow = false;
//            secondSequence = true;
//            for (int i = 0; i< gameSequence.length; i++) {
//                gameSequence[i] = 0;
//            }
//    }

}