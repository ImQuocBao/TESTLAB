package com.example.a19511451_doanngocquocbao_ktth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a19511451_doanngocquocbao_ktth.dao.CourseDao;
import com.example.a19511451_doanngocquocbao_ktth.database.CourseDatabase;
import com.example.a19511451_doanngocquocbao_ktth.database.UserDatabase;
import com.example.a19511451_doanngocquocbao_ktth.database.UserDatabase_Impl;
import com.example.a19511451_doanngocquocbao_ktth.entity.Course;
import com.example.a19511451_doanngocquocbao_ktth.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView lvTodo;
    ArrayList<Course> arrCourse;
    CourseAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ktth-686a6-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("Course");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        EditText edtTitle = findViewById(R.id.edtTitle);
        EditText edtPrice = findViewById(R.id.edtPrice);

        for (User user: UserDatabase.getInstance(this).userDao().getAll()
        ) {
            Log.e("USER", user.email + " Time Login : " + user.getTimeLogin());
        }

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course(edtTitle.getText().toString(), edtPrice.getText().toString());
//                // Add To Local
                CourseDatabase.getInstance(MainActivity.this).courseDao().insertUser(course);
                String pathOject ="";
                int uid = 0;
                for (Course course2: CourseDatabase.getInstance(MainActivity.this).courseDao().getAll()
                     ) {
                    pathOject = String.valueOf(course2.getId());
                    uid = course2.getId();
                }
                course.setId(uid);

                myRef.child(pathOject).setValue(course).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                        loadDataFromFirebase();
                    }
                });
            }
        });
    }

    private void initUI() {
        lvTodo = findViewById(R.id.lvMain);
        arrCourse = new ArrayList<>();

        loadDataFromFirebase();

        adapter = new CourseAdapter(this, R.layout.item_course, arrCourse);
        lvTodo.setAdapter(adapter);
    }

    private void loadDataFromFirebase () {
        // Load data to arrTodo/ Async
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Course> listTodo = new ArrayList<>();

                if(CourseDatabase.getInstance(MainActivity.this).courseDao().getAll().size() < 1) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Course course = data.getValue(Course.class);
                        CourseDatabase.getInstance(MainActivity.this).courseDao().insertUser(course);
                        listTodo.add(course);
                    }
                } else {
//                    for (DataSnapshot data : snapshot.getChildren()) {
//                        Course course = data.getValue(Course.class);
//
//                        listTodo.add(course);
//                    }
                    for (Course course: CourseDatabase.getInstance(MainActivity.this).courseDao().getAll()) {
                        listTodo.add(course);
                    }
                }

                arrCourse.clear();
                arrCourse.addAll(listTodo);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}