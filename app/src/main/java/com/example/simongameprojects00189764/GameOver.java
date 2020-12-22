package com.example.simongameprojects00189764;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

//    int userScore;
    Integer userScore;
    TextView tvScore;
    EditText etUsername;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tvScore = findViewById(R.id.tvScore);
        etUsername = findViewById(R.id.etUsername);

//        Intent sequenceTilt = new Intent(GameOver.this, SequenceTilt.class);

        Intent intent = getIntent();

        userScore = intent.getIntExtra("userScore", 120);
//        userScore = getIntent().getIntExtra("userScore", 13);
        tvScore.setText(String.valueOf(userScore));


    }

    public void playAgain(View view) {
        username = "";
        Intent gameOver = new Intent(GameOver.this, MainActivity.class);
        startActivity(gameOver);
    }

    public void showHighScores(View view) {
        Intent ShowHighScores = new Intent(view.getContext(), HighScores.class);
        startActivity(ShowHighScores);

    }

    public void addToDatabase(View view) {
        username = etUsername.getText().toString();
        Intent HighScores = new Intent(view.getContext(), HighScores.class);
        HighScores.putExtra("userScore", userScore);
        if(username != null) {
            HighScores.putExtra("username", username);
            startActivity(HighScores);
        }

    }
}