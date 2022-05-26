package com.example.a19511451_doanngocquocbao_ktth.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a19511451_doanngocquocbao_ktth.dao.CourseDao;
import com.example.a19511451_doanngocquocbao_ktth.entity.Course;

@Database(entities = {Course.class}, version = 2)
public abstract class CourseDatabase extends RoomDatabase {

    private static CourseDatabase sCourseDatabase;

    public static final String DATABASE_NAME = "Final-database";

    public static CourseDatabase getInstance(Context context) {
        if (sCourseDatabase == null) {
            sCourseDatabase = Room.databaseBuilder(context.getApplicationContext(), CourseDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return sCourseDatabase;
    }

    public abstract CourseDao courseDao();
}
