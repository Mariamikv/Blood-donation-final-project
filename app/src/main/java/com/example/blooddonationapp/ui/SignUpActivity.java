package com.example.blooddonationapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blooddonationapp.R;
import com.example.blooddonationapp.adapter.RecyclerViewAdapter;
import com.example.blooddonationapp.interfaces.ItemClickListener;
import com.example.blooddonationapp.model.UserInfo;
import com.example.blooddonationapp.viewmodel.DataViewModel;

import java.util.List;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements ItemClickListener {

    String[] bloodGroup = { "A RhD positive (A+)", "A RhD negative (A-)", "B RhD positive (B+)",
            "B RhD negative (B-)", "O RhD positive (O+)", "O RhD negative (O-)",
            "AB RhD positive (AB+)", "AB RhD negative (AB-)", "Choose blood group" };

    String userBloodGroup = "";
    Boolean selected = false, temp = false;

    List<UserInfo> user;

    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        Spinner spin = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        spin.setSelection(bloodGroup.length - 1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {
                userBloodGroup = bloodGroup[position];
                selected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                selected = false;
            }
        });

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(new RecyclerViewAdapter.UserDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        if (selected) {
            dataViewModel.getUsersByBloodGroup(userBloodGroup).observe(this, adapter::submitList);
            dataViewModel.getUsersByBloodGroup(userBloodGroup).observe(this, userInfos -> {
                user = userInfos;
            });
        } else {
            dataViewModel.getAllUsers().observe(this, adapter::submitList);
            dataViewModel.getAllUsers().observe(this, userInfos -> {
                user = userInfos;
            });
        }
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(SignUpActivity.this, DetailsActivity.class);

        intent.putExtra("USERNAME", user.get(position).getUsername());
        intent.putExtra("PHONE_NUMBER", user.get(position).getPhoneNumber());
        intent.putExtra("LOCATION", user.get(position).getLocation());
        intent.putExtra("LAST_DONATION_DATE", user.get(position).getLastDonationDate());
        intent.putExtra("DONATION_DAYS", user.get(position).getDonationDays());
        intent.putExtra("BLOOD_GROUP", user.get(position).getBloodGroup());
        intent.putExtra("HEATH_PROBLEMS", user.get(position).getHealthInfo());

        startActivity(intent);
    }
}