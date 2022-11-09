package com.example.blooddonationapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.db.AppDatabase;
import com.example.blooddonationapp.model.UserInfo;
import com.example.blooddonationapp.viewmodel.DataViewModel;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class DonationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] bloodTypes = { "A RhD positive (A+)", "A RhD negative (A-)", "B RhD positive (B+)",
            "B RhD negative (B-)", "O RhD positive (O+)", "O RhD negative (O-)",
            "AB RhD positive (AB+)", "AB RhD negative (AB-)", "Choose blood group" };

    private EditText username, phoneNumber, location, lastDonationDate, donationDays, healthInfo;
    CheckBox checkBox;
    Button submit;

    DataViewModel model;
    String userBloodGroup = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        model = new ViewModelProvider(this).get(DataViewModel.class);

        username = findViewById(R.id.fullNameEditText);
        phoneNumber = findViewById(R.id.phoneNumberEditText);
        location = findViewById(R.id.locationEditText);
        lastDonationDate = findViewById(R.id.lastDonationDateEditText);
        donationDays = findViewById(R.id.nextDonationDateEditText);
        healthInfo = findViewById(R.id.healthTextField);
        checkBox = findViewById(R.id.healthCheckBox);
        submit = findViewById(R.id.submitBtnId);

        Spinner spin = (Spinner) findViewById(R.id.bloodGroup);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        spin.setSelection(bloodTypes.length - 1);

        AtomicInteger healthProblems = new AtomicInteger();

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                healthProblems.set(1);
                healthInfo.setVisibility(View.VISIBLE);
            } else {
                healthProblems.set(0);
            }
        });

        submit.setOnClickListener(v -> {
            saveNewUser(
                    username.getText().toString(),
                    phoneNumber.getText().toString(),
                    location.getText().toString(),
                    userBloodGroup,
                    lastDonationDate.getText().toString(),
                    donationDays.getText().toString(),
                    healthProblems.intValue(),
                    healthInfo.getText().toString()
            );
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void saveNewUser(
            String username,
            String phoneNumber,
            String location,
            String bloodGroup,
            String lastDonationDate,
            String donationDays,
            Integer healthProblems,
            String healthInfo
    ) {

        UserInfo user = new UserInfo();
        user.username = username;
        user.phoneNumber = phoneNumber;
        user.location = location;
        user.bloodGroup = bloodGroup;
        user.lastDonationDate = lastDonationDate;
        user.donationDays = donationDays;
        user.healthInfo = healthInfo;
        user.healthProblems = healthProblems;

        model.insert(user);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userBloodGroup = bloodTypes[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}