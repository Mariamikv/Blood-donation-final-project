package com.example.blooddonationapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;
    @ColumnInfo(name = "location")
    public String location;
    @ColumnInfo(name = "bloodGroup")
    public String bloodGroup;
    @ColumnInfo(name = "lastDonationDate")
    public String lastDonationDate;
    @ColumnInfo(name = "donationDays")
    public String donationDays;
    @ColumnInfo(name = "healthProblems")
    public Integer healthProblems;
    @ColumnInfo(name = "healthInfo")
    public String healthInfo;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public String getDonationDays() {
        return donationDays;
    }

    public void setDonationDays(String donationDays) {
        this.donationDays = donationDays;
    }

    public Integer getHealthProblems() {
        return healthProblems;
    }

    public void setHealthProblems(Integer healthProblems) {
        this.healthProblems = healthProblems;
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }
}
