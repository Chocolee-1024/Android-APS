package com.example.aps.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aps.API.TablePost;
import com.example.aps.Contract.TableContract;
import com.example.aps.Contract.TableRecyclerviewContract;
import com.example.aps.R;

import java.util.List;

public class TableRecyclerviewAdapter extends RecyclerView.Adapter<TableRecyclerviewAdapter.MyViewHolder> implements TableContract.adapter {
    private List<TablePost> listBom;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        private TextView index;
        private TextView materialId;
        private TextView unitId;
        private TextView unitQty;
        private TextView nuseQty;
        private TextView bomkeyName;

        public MyViewHolder(@NonNull View v) {
            super(v);
            itemView=v;
            index=(TextView) v.findViewById(R.id.index);
            materialId=(TextView) v.findViewById(R.id.materialId);
            unitId=(TextView)v.findViewById(R.id.unitId);
            unitQty=(TextView)v.findViewById(R.id.unitQty);
            nuseQty=(TextView)v.findViewById(R.id.nuseQty);
            bomkeyName=(TextView)v.findViewById(R.id.bomkeyName);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View table_recyclerview_item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.derails_recyclerview,parent,false);
        TableRecyclerviewAdapter.MyViewHolder vh=new TableRecyclerviewAdapter.MyViewHolder(table_recyclerview_item);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.index.setText(String.valueOf(position+1));
        holder.materialId.setText(listBom.get(position).getParent().getMaterialId());
        holder.bomkeyName.setText(listBom.get(position).getParent().getBomkeyName());
        holder.nuseQty.setText(listBom.get(position).getNuseQty());
        holder.unitId.setText(listBom.get(position).getUnitQty());
        holder.unitId.setText(listBom.get(position).getUnitId());
    }

    @Override
    public int getItemCount() {
        return listBom.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showBOM(List<TablePost> listBom) {
        this.listBom=listBom;
        Log.e("www",String.valueOf(listBom.size()));
    }
}
