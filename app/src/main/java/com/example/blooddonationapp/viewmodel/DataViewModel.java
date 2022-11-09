package com.example.blooddonationapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blooddonationapp.model.UserInfo;
import com.example.blooddonationapp.repository.UserRepository;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private final LiveData<List<UserInfo>> users;

    public DataViewModel (Application application) {
        super(application);
        userRepository = new UserRepository(application);
        users = userRepository.getAlphabetizedUsers();
    }

    public LiveData<List<UserInfo>> getAllUsers() { return users; }

    public void insert(UserInfo userInfo) {
        userRepository.insert(userInfo);
    }

    public LiveData<List<UserInfo>> getUsersByBloodGroup(String bloodType) {
         return userRepository.getBloodTypeUsers(bloodType);
    }
}
