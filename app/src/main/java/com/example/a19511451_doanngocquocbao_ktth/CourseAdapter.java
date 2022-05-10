package com.example.a19511451_doanngocquocbao_ktth;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private int Layout;
    private List<Course> todoList;

    public CourseAdapter(Context context, int layout, List<Course> todoList) {
        this.context = context;
        Layout = layout;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
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
        Course item = todoList.get(i);

        txtTilte.setText(item.getTitle());
        txtPrice.setText(item.getPrice() + "$");

        return view;
    }
}
