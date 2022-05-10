package com.example.a19511451_doanngocquocbao_ktth;

public class Course {

    private int id;
    private String title, price;

    public Course() {
    }

    public Course(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public Course(int id, String title, String price) {
        this.id = id;
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
