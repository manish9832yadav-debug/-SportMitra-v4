package com.sportmitra;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends Activity {

    int score = 0;
    int balls = 0;
    TextView scoreText, ballsText, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("🏏 SportMitra Cricket");
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);

        scoreText = new TextView(this);
        scoreText.setText("Score: 0");
        scoreText.setTextSize(24);
        scoreText.setGravity(Gravity.CENTER);

        ballsText = new TextView(this);
        ballsText.setText("Balls: 0 / 6");
        ballsText.setTextSize(20);
        ballsText.setGravity(Gravity.CENTER);

        resultText = new TextView(this);
        resultText.setText("Batting start karo!");
        resultText.setTextSize(20);
        resultText.setGravity(Gravity.CENTER);

        Button batButton = new Button(this);
        batButton.setText("🏏 BAT!");

        batButton.setOnClickListener(v -> playBall());

        layout.addView(title);
        layout.addView(scoreText);
        layout.addView(ballsText);
        layout.addView(resultText);
        layout.addView(batButton);

        setContentView(layout);
    }

    void playBall() {
        if (balls >= 6) {
            resultText.setText("🏆 Game Over! Score: " + score);
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
            resultText.setText("😮 Dot Ball!");
        } else {
            resultText.setText("👍 " + run + " Run");
        }

        if (balls == 6) {
            resultText.setText("🏆 Game Over! Score: " + score);
        }
    }
}
