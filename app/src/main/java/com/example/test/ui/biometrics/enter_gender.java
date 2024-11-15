package com.example.test.ui.biometrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class enter_gender extends AppCompatActivity {
    private RadioGroup genderRadioGroup;
    private Button submitGenderButton;

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.enter_gender);

        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        submitGenderButton = findViewById(R.id.submitGenderButton);

        submitGenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = genderRadioGroup.getCheckedRadioButtonId();

                if(selectedId != -1){
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String gender = selectedRadioButton.getText().toString();

                    SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("gender", gender);
                    editor.apply();

                    Intent intent = new Intent(enter_gender.this, enter_weight.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(enter_gender.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
