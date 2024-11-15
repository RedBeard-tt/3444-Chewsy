package com.example.test.ui.biometrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.MainActivity;
import com.example.test.R;

public class activityLevel extends AppCompatActivity {

    private Button sedentaryButton, lightButton, moderateButton, heavyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level); // Ensure this matches your XML filename

        // Initialize buttons
        sedentaryButton = findViewById(R.id.sedentaryRadioButton);
        lightButton = findViewById(R.id.lightExerciseRadioButton);
        moderateButton = findViewById(R.id.moderateExerciseRadioButton);
        heavyButton = findViewById(R.id.heavyExerciseRadioButton);

        // Set up button click listeners
        sedentaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivityLevel("Sedentary");
            }
        });

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivityLevel("Light Exercise");
            }
        });

        moderateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivityLevel("Moderate Exercise");
            }
        });

        heavyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivityLevel("Heavy Exercise");
            }
        });
    }

    private void saveActivityLevel(String activityLevel) {
        // Save activity level to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("activityLevel", activityLevel);
        editor.apply();

        // Navigate to the next activity
        Intent intent = new Intent(activityLevel.this, MainActivity.class); // Replace with your next activity
        startActivity(intent);
        finish(); // Close the current activity
    }
}