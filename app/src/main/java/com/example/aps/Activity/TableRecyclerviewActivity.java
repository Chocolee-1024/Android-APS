package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.aps.Adapter.TableQureyRecyclerviewAdapter;
import com.example.aps.Contract.TableRecyclerviewContract;
import com.example.aps.MoData;
import com.example.aps.R;
import com.example.aps.TokenAndName;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TableRecyclerviewActivity extends AppCompatActivity implements TableRecyclerviewContract.view {
    @BindView(R.id.quantity) TextView quantity;
    @BindView(R.id.tableQueryRV) RecyclerView tableQueryRv;
    @BindView(R.id.RecyclerViewIncu) View RecyclerViewIncu;
    private RecyclerView.LayoutManager tableQueryManager;
    private TableQureyRecyclerviewAdapter tableQueryAdapter;
    private MoData moData;
    private TokenAndName tokenAndName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_recyclerview);

        ButterKnife.bind(this);
        IncludeLayout includeLayout=new IncludeLayout();
        ButterKnife.bind(includeLayout,RecyclerViewIncu);
        //拿取userName
        tokenAndName=new TokenAndName(this);
        includeLayout.userName.setText(tokenAndName.takeName());
        //LinearLayoutManager
        tableQueryManager = new LinearLayoutManager(this);
        tableQueryRv.setLayoutManager(tableQueryManager);
        //Adapter
        tableQueryAdapter=new TableQureyRecyclerviewAdapter(this);
        tableQueryRv.setAdapter(tableQueryAdapter);
        //把Mo資料傳進adapter
        moData=new MoData();
        tableQueryAdapter.loadData(moData.takeMoData());

        includeLayout.backtv.setText("進度表查詢");
        quantity.setText("共"+moData.takeMoData().size()+"比");
        //按backBt返回TableQuery
        includeLayout.backbt.setOnClickListener(view -> {
            Intent intent = new Intent(TableRecyclerviewActivity.this, TableQueryAcivity.class);
            startActivity(intent);
        });
    }

    //讓Table判斷是從TableRecyclerview進入的
    @Override
    public void selectTable(int position) {
        Intent intent = new Intent(TableRecyclerviewActivity.this, TableActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("whichNumber","3");
        //傳送點擊資料為第幾比
        bundle.putInt("position",position);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //IncludeLayout和ButterKnife綁定元件
    public class IncludeLayout {
        @BindView(R.id.backBut)ImageButton backbt;
        @BindView(R.id.backtv)TextView backtv;
        @BindView(R.id.backUserName) TextView userName;
    }
}