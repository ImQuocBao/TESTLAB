package com.example.a19511451_doanngocquocbao_ktth.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a19511451_doanngocquocbao_ktth.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertUser(User user);
}
