package com.example.blooddonationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.blooddonationapp.R;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    TextView username, phoneNumber, location, bloodGroup, lastDonationDate,  donationDays, healthInfo;
    Button callButton;

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String username1 = getIntent().getStringExtra("USERNAME");
        String phoneNumber1 = getIntent().getStringExtra("PHONE_NUMBER");
        String location1 = getIntent().getStringExtra("LOCATION");
        String bloodGroup1 = getIntent().getStringExtra("BLOOD_GROUP");
        String lastDonationDate1 = getIntent().getStringExtra("LAST_DONATION_DATE");
        String donationDats1 = getIntent().getStringExtra("DONATION_DAYS");
        String healthInfo1 = getIntent().getStringExtra("HEATH_PROBLEMS");

        username = findViewById(R.id.fullnameTxt);
        phoneNumber = findViewById(R.id.phoneNumberTxt);
        location = findViewById(R.id.locationTxt);
        bloodGroup = findViewById(R.id.bloodGroupTxt);
        lastDonationDate = findViewById(R.id.lastDonationDateTxt);
        donationDays = findViewById(R.id.donationDaysTxt);
        healthInfo = findViewById(R.id.healthProblemsInfoTxt);

        callButton = findViewById(R.id.call);

        username.setText("Username: " + username1);
        phoneNumber.setText("Phone number: " + phoneNumber1);
        location.setText("Location: " + location1);
        lastDonationDate.setText("Last donation date: " + lastDonationDate1);
        donationDays.setText("Donation days: " + donationDats1);
        if (healthInfo1.isEmpty()) {
            healthInfo.setText("No information about health.");
        } else {
            healthInfo.setText(healthInfo1);
        }
        bloodGroup.setText("Blood group: " + bloodGroup1);

        callButton.setOnClickListener(v -> {
            String phone = "+995" + phoneNumber1;
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);

            phoneNumber.setTextColor(R.color.purple_200);
        });
    }
}