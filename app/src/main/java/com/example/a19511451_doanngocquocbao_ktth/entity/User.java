package com.example.a19511451_doanngocquocbao_ktth.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public int uid;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "timeLogin")
    public String timeLogin;

    public User() {
    }

    public User(String email, String timeLogin) {
        this.email = email;
        this.timeLogin = timeLogin;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(String timeLogin) {
        this.timeLogin = timeLogin;
    }
}
