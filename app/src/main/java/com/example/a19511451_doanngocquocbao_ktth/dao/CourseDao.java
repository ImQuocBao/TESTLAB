package com.example.a19511451_doanngocquocbao_ktth.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a19511451_doanngocquocbao_ktth.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Insert
    void insertUser(Course course);

    @Delete
    void delete(Course course);
}
