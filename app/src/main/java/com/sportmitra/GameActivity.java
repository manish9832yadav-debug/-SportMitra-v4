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

public class GameActivity extends Activity {

    int score = 0;
    int wickets = 0;
    int balls = 0;

    TextView scoreText;
    TextView overText;
    TextView resultText;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("🏏 SPORTMITRA CRICKET");
        title.setTextSize(28);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        scoreText = new TextView(this);
        scoreText.setTextSize(25);
        scoreText.setGravity(Gravity.CENTER);

        overText = new TextView(this);
        overText.setTextSize(20);
        overText.setGravity(Gravity.CENTER);

        resultText = new TextView(this);
        resultText.setText("🏏 Batting start karo!");
        resultText.setTextSize(20);
        resultText.setGravity(Gravity.CENTER);

        Button batButton = new Button(this);
        batButton.setText("🏏 BAT!");

        Button homeButton = new Button(this);
        homeButton.setText("🏠 HOME");

        layout.addView(title);
        layout.addView(scoreText);
        layout.addView(overText);
        layout.addView(resultText);
        layout.addView(batButton);
        layout.addView(homeButton);

        setContentView(layout);

        updateScore();

        batButton.setOnClickListener(v -> playBall());

        homeButton.setOnClickListener(v -> finish());
    }

    void playBall() {

        if (balls >= 12 || wickets >= 3) {
            resultText.setText("🏆 GAME OVER!\nScore: " + score);
            return;
        }

        balls++;

        int event = random.nextInt(10);

        if (event == 0) {
            wickets++;
            resultText.setText("😮 OUT!");
        } else {

            int[] runs = {0, 1, 2, 3, 4, 6};
            int run = runs[random.nextInt(runs.length)];

            score += run;

            if (run == 6) {
                resultText.setText("🔥 SIX!");
            } else if (run == 4) {
                resultText.setText("🎉 FOUR!");
            } else if (run == 0) {
                resultText.setText("😮 DOT BALL!");
            } else {
                resultText.setText("👍 " + run + " RUN");
            }
        }

        updateScore();

        if (balls >= 12 || wickets >= 3) {
            resultText.setText(
                "🏆 GAME OVER!\nFinal Score: " + score + "/" + wickets
            );
        }
    }

    void updateScore() {

        scoreText.setText(
            "Score: " + score + "/" + wickets
        );

        int overs = balls / 6;
        int currentBall = balls % 6;

        overText.setText(
            "Overs: " + overs + "." + currentBall
        );
    }
}
