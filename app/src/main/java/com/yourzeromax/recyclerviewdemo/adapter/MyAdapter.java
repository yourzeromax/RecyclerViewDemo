package com.yourzeromax.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yourzeromax.recyclerviewdemo.Bean.Student;
import com.yourzeromax.recyclerviewdemo.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mContext;
    List<Student> students;
    int layoutId;


    public MyAdapter(Context context, List<Student> students, int layoutId) {
        this.mContext = context;
        this.students = students;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Student student = students.get(i);
        myViewHolder.tvId.setText(student.id+"");
        myViewHolder.tvName.setText(student.name);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tv_id);
            this.tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
