package com.sportmitra;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends Activity {

    LinearLayout layout;
    TextView title;
    int score = 0;
    int balls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    void showHome() {

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(35, 35, 35, 35);
        layout.setBackgroundColor(Color.WHITE);

        title = new TextView(this);
        title.setText("🏏 SportMitra");
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView welcome = new TextView(this);
        welcome.setText("\nWelcome to SportMitra!\n\nPlay • Score • Win 🏆");
        welcome.setTextSize(21);
        welcome.setGravity(Gravity.CENTER);

        Button playButton = new Button(this);
        playButton.setText("🎮 PLAY CRICKET");

        Button profileButton = new Button(this);
        profileButton.setText("👤 PROFILE");

        Button settingsButton = new Button(this);
        settingsButton.setText("⚙️ SETTINGS");

        playButton.setOnClickListener(v -> startGame());

        profileButton.setOnClickListener(v -> {
            welcome.setText("👤 PROFILE\n\nPlayer: SportMitra Player\nLevel: 1\nWins: 0");
        });

        settingsButton.setOnClickListener(v -> {
            welcome.setText("⚙️ SETTINGS\n\nSound: ON\nDifficulty: Easy");
        });

        layout.addView(title);
        layout.addView(welcome);
        layout.addView(playButton);
        layout.addView(profileButton);
        layout.addView(settingsButton);

        setContentView(layout);
    }

    void startGame() {

        score = 0;
        balls = 0;

        layout.removeAllViews();

        TextView gameTitle = new TextView(this);
        gameTitle.setText("🏏 CRICKET MATCH");
        gameTitle.setTextSize(30);
        gameTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        gameTitle.setGravity(Gravity.CENTER);

        TextView scoreText = new TextView(this);
        scoreText.setText("Score: 0");
        scoreText.setTextSize(25);
        scoreText.setGravity(Gravity.CENTER);

        TextView ballsText = new TextView(this);
        ballsText.setText("Balls: 0 / 6");
        ballsText.setTextSize(20);
        ballsText.setGravity(Gravity.CENTER);

        TextView resultText = new TextView(this);
        resultText.setText("\nBatting start karo!");
        resultText.setTextSize(21);
        resultText.setGravity(Gravity.CENTER);

        Button batButton = new Button(this);
        batButton.setText("🏏 BAT!");

        Button homeButton = new Button(this);
        homeButton.setText("🏠 HOME");

        batButton.setOnClickListener(v -> {

            if (balls >= 6) {
                resultText.setText("🏆 GAME OVER!\nFinal Score: " + score);
                return;
            }

            balls++;

            int[] runs = {0, 1, 2, 3, 4, 6};
            int run = runs[new Random().nextInt(runs.length)];

            score += run;

            scoreText.setText("Score: " + score);
            ballsText.setText("Balls: " + balls + " / 6");

            if (run == 6) {
                resultText.setText("🔥 SIX!");
            } else if (run == 4) {
                resultText.setText("🎉 FOUR!");
            } else if (run == 0) {
                resultText.setText("😮 DOT BALL!");
            } else {
                resultText.setText("👍 " + run + " RUN");
            }

            if (balls == 6) {
                resultText.setText(
                    "🏆 GAME OVER!\nFinal Score: " + score
                );
            }
        });

        homeButton.setOnClickListener(v -> showHome());

        layout.addView(gameTitle);
        layout.addView(scoreText);
        layout.addView(ballsText);
        layout.addView(resultText);
        layout.addView(batButton);
        layout.addView(homeButton);

        setContentView(layout);
    }
}
