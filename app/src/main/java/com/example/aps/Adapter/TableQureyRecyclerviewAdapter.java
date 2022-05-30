package com.example.aps.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aps.API.MoPost;
import com.example.aps.Contract.TableRecyclerviewContract;
import com.example.aps.R;

import java.util.List;

public class TableQureyRecyclerviewAdapter extends RecyclerView.Adapter<TableQureyRecyclerviewAdapter.MyViewHolder> implements TableRecyclerviewContract.adapter{
    private TableRecyclerviewContract.view callback;
    private List<MoPost> moData;
    public TableQureyRecyclerviewAdapter(TableRecyclerviewContract.view view){
        this.callback=view;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View itemview;
        public ImageView selectTable;
        public TextView tableNuber;
        public TextView mo_id;
        public TextView so_id;
        public TextView item_id;
        public TextView customer;
        public TextView qty;
        public TextView demand_complete_date;
        public TextView online_date;
        public TextView tech_routing_name;

        public MyViewHolder(@NonNull View v) {
            super(v);
            itemview=v;
            selectTable= (ImageView) v.findViewById(R.id.selectTable);
            tableNuber=(TextView)v.findViewById(R.id.tableNuber);
            mo_id=(TextView)v.findViewById(R.id.mo_id);
            so_id=(TextView)v.findViewById(R.id.so_id);
            item_id=(TextView)v.findViewById(R.id.item_id);
            customer=(TextView)v.findViewById(R.id.customer);
            qty=(TextView)v.findViewById(R.id.qty);
            demand_complete_date=(TextView)v.findViewById(R.id.demand_complete_date);
            online_date=(TextView)v.findViewById(R.id.online_date);
            tech_routing_name=(TextView)v.findViewById(R.id.tech_routing_name);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View table_query_recyclerview_item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_recyclerview_item,parent,false);
        MyViewHolder vh=new MyViewHolder(table_query_recyclerview_item);
        return vh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tableNuber.setText(String.valueOf(position+1));
        holder.mo_id.setText(moData.get(position).getMo_id());
        holder.so_id.setText(moData.get(position).getSo_id());
        holder.item_id.setText(moData.get(position).getItem_id());
        holder.customer.setText(moData.get(position).getCustomer());
        holder.qty.setText("數量："+moData.get(position).getQty());
        holder.demand_complete_date.setText("結關日："+moData.get(position).getDemand_complete_date());
        holder.online_date.setText("上線日："+moData.get(position).getOnline_date());
        holder.tech_routing_name.setText(moData.get(position).getRelatedTechRoute().getTech_routing_name());
        holder.selectTable.setOnClickListener(new View.OnClickListener() {
            @Override
            //傳回點擊的資料位址
            public void onClick(View view) {
                callback.selectTable(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moData.size();
    }

    //從view傳入Mo資料
    @Override
    public void loadData(List<MoPost> moData) {
        this.moData=moData;

    }
}
