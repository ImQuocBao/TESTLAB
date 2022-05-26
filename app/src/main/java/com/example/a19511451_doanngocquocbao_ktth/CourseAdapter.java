package com.example.a19511451_doanngocquocbao_ktth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.a19511451_doanngocquocbao_ktth.database.CourseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.a19511451_doanngocquocbao_ktth.entity.Course;
import java.util.List;

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private int Layout;
    private List<Course> courseList;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ktth-686a6-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("Course");

    public CourseAdapter(Context context, int layout, List<Course> todoList) {
        this.context = context;
        Layout = layout;
        this.courseList = todoList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(Layout, null);

        TextView txtTilte = view.findViewById(R.id.textView5);
        TextView txtPrice = view.findViewById(R.id.textView6);
        Course item = courseList.get(i);

        txtTilte.setText(item.getTitle());
        txtPrice.setText(item.getPrice() + "$");

        ImageView btnRemove = view.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CourseDatabase.getInstance(view.getContext()).courseDao().delete(courseList.get(i));

                myRef.child(courseList.get(i).getId() + "").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Remove Success", "");
                        } else {
                            Log.d("Error something wrong!!!", "");
                        }
                    }
                });
            }
        });

        ImageView btnEdit = view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit_course.class);
                Bundle b = new Bundle();
                b.putInt("id", courseList.get(i).getId());
                b.putString("title", courseList.get(i).getTitle());
                b.putString("price", courseList.get(i).getPrice());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
