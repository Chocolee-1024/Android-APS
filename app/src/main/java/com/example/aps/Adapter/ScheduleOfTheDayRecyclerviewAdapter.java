package com.example.aps.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aps.Contract.ScheduleOfTheDayContract;
import com.example.aps.R;

public class ScheduleOfTheDayRecyclerviewAdapter extends RecyclerView.Adapter<ScheduleOfTheDayRecyclerviewAdapter.MyViewHolder> {
    private ScheduleOfTheDayContract.View callback;
    public ScheduleOfTheDayRecyclerviewAdapter(ScheduleOfTheDayContract.View view){
        this.callback=view;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView tv1;
            public TextView tv2;
            public TextView tv3;
            public TextView tv4;
            public TextView tv5;
            public TextView tv6;
            public TextView tv7;
            public TextView tv8;
            public TextView tv9;
            public View itemview;
            public ImageView  selectTable;
            public MyViewHolder(@NonNull View v) {
                super(v);
                tv1=(TextView)v.findViewById(R.id.tableNuber);
                itemview=v;
                selectTable= (ImageView) v.findViewById(R.id.selectTable);
            }
        }


    @NonNull
    @Override
    public ScheduleOfTheDayRecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View schedule_of_the_day_recyclerview_item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_of_the_day_recyclerview_item,parent,false);
        MyViewHolder vh =new MyViewHolder(schedule_of_the_day_recyclerview_item);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleOfTheDayRecyclerviewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv1.setText(String.valueOf(position+1));
        holder.selectTable.setOnClickListener(view -> callback.selectTable(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
