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

    int score = 0;
    int wickets = 0;
    int balls = 0;

    int target = 0;
    boolean secondInnings = false;

    TextView scoreText;
    TextView overText;
    TextView resultText;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    void showHome() {

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("🏏 SportMitra");
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView welcome = new TextView(this);
        welcome.setText("\n🏆 Cricket Challenge\n\nScore • Play • Win");
        welcome.setTextSize(22);
        welcome.setGravity(Gravity.CENTER);

        Button play = new Button(this);
        play.setText("🎮 START MATCH");

        play.setOnClickListener(v -> startFirstInnings());

        layout.addView(title);
        layout.addView(welcome);
        layout.addView(play);

        setContentView(layout);
    }

    void startFirstInnings() {

        score = 0;
        wickets = 0;
        balls = 0;
        target = 0;
        secondInnings = false;

        showGameScreen();
    }

    void showGameScreen() {

        layout.removeAllViews();

        TextView title = new TextView(this);

        if (secondInnings) {
            title.setText("🏏 2nd INNINGS");
        } else {
            title.setText("🏏 1st INNINGS");
        }

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
        resultText.setTextSize(20);
        resultText.setGravity(Gravity.CENTER);

        Button bat = new Button(this);
        bat.setText("🏏 BAT");

        Button home = new Button(this);
        home.setText("🏠 HOME");

        layout.addView(title);
        layout.addView(scoreText);
        layout.addView(overText);
        layout.addView(resultText);
        layout.addView(bat);
        layout.addView(home);

        updateScore();

        bat.setOnClickListener(v -> playBall());

        home.setOnClickListener(v -> showHome());
    }

    void playBall() {

        if (balls >= 12 || wickets >= 3) {
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
                resultText.setText("• DOT BALL");
            } else {
                resultText.setText("👍 " + run + " RUN");
            }
        }

        updateScore();

        if (secondInnings && score >= target) {

            resultText.setText(
                    "🏆 YOU WIN!\nTarget: " + target
            );

            return;
        }

        if (balls >= 12 || wickets >= 3) {

            if (!secondInnings) {

                target = score + 1;

                resultText.setText(
                        "🏏 1st Innings Over!\nTarget: " + target
                );

                Button next = new Button(this);
                next.setText("▶️ START 2nd INNINGS");

                layout.addView(next);

                next.setOnClickListener(v -> {

                    score = 0;
                    wickets = 0;
                    balls = 0;

                    secondInnings = true;

                    showGameScreen();
                });

            } else {

                resultText.setText(
                        "😔 YOU LOSE!\nTarget: " + target +
                        "\nYour Score: " + score
                );
            }
        }
    }

    void updateScore() {

        scoreText.setText(
                "Score: " + score + "/" + wickets
        );

        int overs = balls / 6;
        int ball = balls % 6;

        overText.setText(
                "Overs: " + overs + "." + ball
        );

        if (secondInnings) {

            resultText.setText(
                    "🎯 Target: " + target
            );
        }
    }
}
