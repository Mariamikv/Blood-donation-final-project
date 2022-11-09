package com.example.blooddonationapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.blooddonationapp.model.UserInfo;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserInfo ORDER BY username ASC")
    LiveData<List<UserInfo>> getAlphabetizedUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(UserInfo...userInfos);

    @Query("Select * from UserInfo where bloodGroup = :group")
    LiveData<List<UserInfo>> getBloodGroupUserList(String group);
}
