package com.example.safeit.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.safeit.Model.Password;

import java.util.List;

@androidx.room.Dao
public interface PasswordDao {

    @Query("SELECT * FROM Password_Database")
    LiveData<List<Password>> getAllPasswords();

    @Insert
    void insertPassword(Password... passwords);

    @Query("DELETE FROM Password_Database WHERE id=:id")
    void deletePassword(int id);

    @Update
    void updatePassword(Password passwords);

}
