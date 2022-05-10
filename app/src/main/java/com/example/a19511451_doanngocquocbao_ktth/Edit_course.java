package com.example.a19511451_doanngocquocbao_ktth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_course extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ktth-686a6-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("Course");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        String txtTitle = getIntent().getExtras().getString("title");
        String txtPrice = getIntent().getExtras().getString("price");
        int id = getIntent().getExtras().getInt("id");


        EditText edtCourse = findViewById(R.id.edtTitle);
        EditText edtPrice = findViewById(R.id.edtPrice);

        edtCourse.setText(txtTitle);
        edtPrice.setText(txtPrice);

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child(id + "").child("title").setValue(edtCourse.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Edit_course.this, "Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Edit_course.this, "Update Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myRef.child(id + "").child("price").setValue(edtPrice.getText().toString());
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Edit_course.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}