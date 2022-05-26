package com.example.a19511451_doanngocquocbao_ktth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a19511451_doanngocquocbao_ktth.database.UserDatabase;
import com.example.a19511451_doanngocquocbao_ktth.database.UserDatabase_Impl;
import com.example.a19511451_doanngocquocbao_ktth.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPass = findViewById(R.id.edtPassword);
        TextView tvSignup = findViewById(R.id.tvSignup);

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, MainActivity.class);

                            Date currentTime = Calendar.getInstance().getTime();
                            UserDatabase.getInstance(Login.this).userDao().insertUser(new User(edtEmail.getText().toString(), currentTime+""));

                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login failed!! Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}