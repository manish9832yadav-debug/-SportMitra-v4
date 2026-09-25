package com.sportmitra;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    LinearLayout layout;

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
        title.setTextSize(36);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView welcome = new TextView(this);
        welcome.setText(
                "\n🏆 Cricket Challenge\n\n" +
                "Score • Play • Win"
        );
        welcome.setTextSize(22);
        welcome.setGravity(Gravity.CENTER);

        Button play = new Button(this);
        play.setText("🎮 START MATCH");

        Button profile = new Button(this);
        profile.setText("👤 PROFILE");

        Button leaderboard = new Button(this);
        leaderboard.setText("🏆 LEADERBOARD");

        play.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this, GameActivity.class);

            startActivity(intent);
        });

        profile.setOnClickListener(v -> {

            welcome.setText(
                    "👤 PROFILE\n\n" +
                    "Player: SportMitra Player\n" +
                    "Level: 1\n" +
                    "Matches: 0\n" +
                    "Wins: 0"
            );
        });

        leaderboard.setOnClickListener(v -> {

            welcome.setText(
                    "🏆 LEADERBOARD\n\n" +
                    "1. Player 1 - 100\n" +
                    "2. Player 2 - 80\n" +
                    "3. Player 3 - 60"
            );
        });

        layout.addView(title);
        layout.addView(welcome);
        layout.addView(play);
        layout.addView(profile);
        layout.addView(leaderboard);

        setContentView(layout);
    }
}
