package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aps.API.TableQueryPost;
import com.example.aps.Contract.TableQueryContract;
import com.example.aps.Presenter.TableQueryPresenter;
import com.example.aps.R;
import com.example.aps.TokenAndName;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableQueryAcivity extends AppCompatActivity implements TableQueryContract.view {
    @BindView(R.id.onlineDate) EditText onlineDate;
    @BindView(R.id.customerName) EditText customer_name;
    @BindView(R.id.saleOrder) EditText so_id;
    @BindView(R.id.confirm) Button confirm;
    @BindView(R.id.date) Button date;
    @BindView(R.id.queryIncu) View queryIncu;
    private TableQueryPresenter presenter;
    private TokenAndName tokenAndName;
    private AlertDialog.Builder dialogList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_query);
        //綁定include
        ButterKnife.bind(this);
        IncludeLayout includedLayout=new IncludeLayout();
        ButterKnife.bind(includedLayout,queryIncu);

        presenter = new TableQueryPresenter(this);
        tokenAndName = new TokenAndName((Context) this);

        includedLayout.userName.setText(tokenAndName.takeName());
        includedLayout.backtv.setText("進度表查詢");

        //按backBt返回Main
        includedLayout.backbt.setOnClickListener(view -> {
            Intent intent = new Intent(TableQueryAcivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
        //呼叫日期表
        calendar(this);
    }


    //模糊搜尋Dialog
    private void fuzzySearch(List<TableQueryPost> listTableQuery,int which)
    {
        /**-------AlertDialog只能放陣列，所以把List<TableQueryPost>轉換-------*/
        String[] data = new String[listTableQuery.size()];
        for(int i=0;i<listTableQuery.size();i++)
            if(which==1)
                data[i]=listTableQuery.get(i).getSo_id();
            else
                data[i]=listTableQuery.get(i).getCustomer_name();

        dialogList= new AlertDialog.Builder(TableQueryAcivity.this);
        //Dialog的Title
        dialogList.setTitle("客戶名稱");
        //判斷按下Dialog的第幾個
        dialogList.setItems(data, (dialogInterface, i) -> {
            if(which==1)
                so_id.setText(data[i]);
            else
                customer_name.setText(data[i]);
        });
        dialogList.show();
    }

    //日期表
    private void calendar(Context getContext) {
        //在點了按鈕後才跳出日曆
        date.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            //取得Calender物件資訊
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            //取出年月日
            new DatePickerDialog(getContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                      //這是我希望它選取後顯示上去的文字格式
                    onlineDate.setText(String.format(getResources().getString(R.string.current_time),year,month,day));
                    //setText上去editText~
                }
            },year,month,day).show(); });
    }

    //模糊搜尋"訂單單號"
    public void clickQuerySaleOrder(View view) {
        presenter.getQuerySaleOrder(so_id.getText().toString());
    }

    //拿取模糊搜尋"訂單單號"
    @Override
    public void takeQuerySaleOrder(List<TableQueryPost> listSaleOrder) {
        //呼叫模糊搜尋
        fuzzySearch(listSaleOrder,1);
    }

    //模糊搜尋"客戶名稱"
    public void clickQueryCustomerName(View view) {
        presenter.getQueryCustomerName(customer_name.getText().toString());
    }

    //拿取模糊搜尋"客戶名稱"
    @Override
    public void takeQueryCustomerName(List<TableQueryPost> listCustomerName) {
        //呼叫模糊搜尋
        fuzzySearch(listCustomerName,2);
    }

    //找尋Mo
    public void clickConfirm(View view) {
        presenter.getQueryOrderNumber(so_id.getText().toString(),onlineDate.getText().toString(),customer_name.getText().toString());
    }

    //成功拿到資料進入TableRecyclerview
    @Override
    public void takeQueryOrderNumber() {
        Intent intent = new Intent(TableQueryAcivity.this, TableRecyclerviewActivity.class);
        startActivity(intent);
    }

    //Mo沒有資料
    @Override
    public void noData() {
        Toast.makeText((Context)this, "查無資料", Toast.LENGTH_SHORT).show();
    }

    //沒有輸入資料
    @Override
    public void inputNoData() {
        Toast.makeText((Context)this, "請輸入資料", Toast.LENGTH_SHORT).show();
    }

    //IncludeLayout和ButterKnife綁定元件
    public class IncludeLayout {
        @BindView(R.id.backBut)ImageButton backbt;
        @BindView(R.id.backtv)TextView backtv;
        @BindView(R.id.backUserName) TextView userName;
    }
}