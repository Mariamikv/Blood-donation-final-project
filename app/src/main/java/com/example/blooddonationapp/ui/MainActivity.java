package com.example.blooddonationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.ui.DonationActivity;
import com.example.blooddonationapp.ui.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDonate = findViewById(R.id.donateBtnId);
        Button buttonSignUp = findViewById(R.id.signUpBtnId);

        buttonDonate.setOnClickListener(v -> {
            Intent intent = new Intent(this, DonationActivity.class);
            startActivity(intent);
        });

        buttonSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}