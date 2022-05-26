package com.example.a19511451_doanngocquocbao_ktth.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int id;

    private String title;
    private String price;

    public Course() {
    }

    public Course(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
