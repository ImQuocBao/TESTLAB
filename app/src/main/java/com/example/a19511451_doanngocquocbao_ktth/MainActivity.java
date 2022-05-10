package com.example.a19511451_doanngocquocbao_ktth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTodo;
    ArrayList<Course> arrCourse;
    CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        lvTodo = findViewById(R.id.lvMain);
        arrCourse = new ArrayList<>();

        arrCourse.add(new Course(1,"English for kids", "12"));
        arrCourse.add(new Course(2,"Learn HTML,Css For Beginner", "89.0"));

//        loadDataFromFirebase();

        adapter = new CourseAdapter(this, R.layout.item_course, arrCourse);
        lvTodo.setAdapter(adapter);
    }
}