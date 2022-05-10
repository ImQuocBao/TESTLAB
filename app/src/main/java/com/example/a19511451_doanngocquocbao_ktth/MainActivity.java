package com.example.a19511451_doanngocquocbao_ktth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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


        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rnd = new Random();
                int number = rnd.nextInt(999999);

                Course course = new Course(edtTitle.getText().toString(), edtPrice.getText().toString());
                course.setId(number);

                String pathOject = String.valueOf(course.getId());

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
                for (DataSnapshot data : snapshot.getChildren()) {
                    Course course = data.getValue(Course.class);
                    listTodo.add(course);
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