package com.example.blooddonationapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.blooddonationapp.db.AppDatabase;
import com.example.blooddonationapp.db.UserDao;
import com.example.blooddonationapp.model.UserInfo;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserInfo>> allUsers;
    private LiveData<List<UserInfo>> bloodTypeUsers;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAlphabetizedUsers();

    }

    public LiveData<List<UserInfo>> getAlphabetizedUsers() {
        return allUsers;
    }

    public void insert(UserInfo userInfo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insertUsers(userInfo);
        });
    }

    // ეს დავამატე რო blood group-ს რომ ავირჩევთ სპინერში წამოიღოს მონაცემები,
    // მაგრამ სპინერიდან მერე აფდეითს ვერ ვაკეთებს და ვერ მოდის:დდ
    public LiveData<List<UserInfo>> getBloodTypeUsers (String bloodGroup) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
             bloodTypeUsers = userDao.getBloodGroupUserList(bloodGroup);
        });
        return bloodTypeUsers;
    }
}