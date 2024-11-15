package com.example.test.ui.biometrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class enter_height extends AppCompatActivity {

    private Spinner heightSpinner;
    private Button submitHeightButton;
    private String selectedHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_height); // Ensure this matches your XML filename

        // Initialize views
        heightSpinner = findViewById(R.id.height_spinner);
        submitHeightButton = findViewById(R.id.submitHeightButton);

        // Populate spinner with height options
        String[] heightOptions = generateHeightOptions();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, heightOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightSpinner.setAdapter(adapter);

        // Listen for spinner selection
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHeight = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedHeight = null;
            }
        });

        // Submit button functionality
        submitHeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedHeight != null) {
                    // Save selected height to SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("height", selectedHeight);
                    editor.apply();

                    // Navigate to the next activity
                    Intent intent = new Intent(enter_height.this, activityLevel.class); // Replace with your next activity
                    startActivity(intent);
                    finish(); // Close the current activity
                } else {
                    Toast.makeText(enter_height.this, "Please select your height", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to generate height options (e.g., in feet and inches)
    private String[] generateHeightOptions() {
        String[] options = new String[97]; // Heights from 4'0" to 7'11" (in total 97 options)
        int index = 0;
        for (int feet = 4; feet <= 7; feet++) {
            for (int inches = 0; inches <= 11; inches++) {
                options[index++] = feet + "'" + inches + "\"";
            }
        }
        return options;
    }
}

