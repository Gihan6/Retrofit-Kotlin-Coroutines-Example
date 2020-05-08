package com.qdream.retrofit_kotlin_coroutines_example.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.qdream.retrofit_kotlin_coroutines_example.model.User;


@Dao
public interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Update
    void update(User... user);

    @Update
    void updateUserDate(User userData);

    @Query("select * from User where active = 1;")
    User checkIfUserExist();

    @Query("DELETE FROM User;")
    void clear();
}
