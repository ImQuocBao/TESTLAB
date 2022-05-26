package com.example.a19511451_doanngocquocbao_ktth.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a19511451_doanngocquocbao_ktth.dao.UserDao;
import com.example.a19511451_doanngocquocbao_ktth.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase sUserDatabase;

    public static final String DATABASE_NAME = "Final-database1";

    public static UserDatabase getInstance(Context context) {
        if (sUserDatabase == null) {
            sUserDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return sUserDatabase;
    }

    public abstract UserDao userDao();
}
