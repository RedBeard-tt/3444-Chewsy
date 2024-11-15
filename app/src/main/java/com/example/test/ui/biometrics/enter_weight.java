package com.example.test.ui.biometrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class enter_weight extends AppCompatActivity {
    private EditText weightEditText;
    private Button submitWeightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_weight);

        weightEditText = findViewById(R.id.weightEditText);
        submitWeightButton = findViewById(R.id.submitWeightButton);

        submitWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightText = weightEditText.getText().toString();
                if(!weightText.isEmpty()){
                    double weight = Double.parseDouble(weightText);

                    SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putFloat("weight", (float) weight);
                    editor.apply();

                    Intent intent = new Intent(enter_weight.this, enter_height.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(enter_weight.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
