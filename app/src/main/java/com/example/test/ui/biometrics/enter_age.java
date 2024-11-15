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

public class enter_age extends AppCompatActivity {
    private EditText ageEditText;
    private Button submitAgeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_age);

        ageEditText = findViewById(R.id.ageEditText);
        submitAgeButton = findViewById(R.id.submitAgeButton);

        submitAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String ageText = ageEditText.getText().toString();
                if(!ageText.isEmpty()){
                    int age = Integer.parseInt(ageText);

                    SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("age", age);
                    editor.apply();

                    Intent intent = new Intent(enter_age.this, enter_gender.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(enter_age.this, "Please enter your age", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
