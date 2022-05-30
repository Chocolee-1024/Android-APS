package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.aps.API.MoPost;
import com.example.aps.API.TablePost;
import com.example.aps.Adapter.TableRecyclerviewAdapter;
import com.example.aps.Contract.TableContract;
import com.example.aps.MoData;
import com.example.aps.Presenter.TablePresenter;
import com.example.aps.R;
import com.example.aps.TokenAndName;
import com.google.android.material.internal.VisibilityAwareImageButton;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableActivity extends AppCompatActivity implements TableContract.view {
    @BindView(R.id.radioGroup) RadioGroup radioGroup;
    @BindView(R.id.radioButton1)  RadioButton radioButton1;
    @BindView(R.id.radioButton2)  RadioButton radioButton2;
    @BindView(R.id.radioButton3)  RadioButton radioButton3;
    @BindView(R.id.radioButton4)  RadioButton radioButton4;
    @BindView(R.id.radioButton5)  RadioButton radioButton5;
    @BindView(R.id.nextPage)      ImageButton nextPage;
    @BindView(R.id.previousPage)  ImageButton previousPage;
    @BindView(R.id.detailsLayout) LinearLayout detailsLayout;
    @BindView(R.id.titleIncu)     View titleIncu;
    @BindView(R.id.bomIncu)       View bomIncu;
    private int whichNumber;
    private int position;
    private TablePresenter presenter;
    private View detailsA;
    private View detailsB;
    private TokenAndName tokenAndName;
    private TableRecyclerviewAdapter tableAdapter;
    private RecyclerView.LayoutManager tableManger;
    private List<TablePost> listBom;
    private MoData modata;
    private List<MoPost> listMo;

    private BomIncuLayout bomIncuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);

        //bomIncu和ButterKnife綁定
        bomIncuLayout=new BomIncuLayout();
        ButterKnife.bind(bomIncuLayout,bomIncu);

        //titleIncu和ButterKnife綁定
        TitleIncuLayout titleIncuLayout=new TitleIncuLayout();
        ButterKnife.bind(titleIncuLayout,titleIncu);

        detailsA = getLayoutInflater().inflate(R.layout.details_a, null);
        detailsB = getLayoutInflater().inflate(R.layout.details_b, null);

        Bundle bundle = getIntent().getExtras();

        //拿取並放入userName
        tokenAndName=new TokenAndName(this);
        titleIncuLayout.userName.setText(tokenAndName.takeName());
        //拿取modata
        modata=new MoData();
        listMo=modata.takeMoData();

        radioButton2.setChecked(true);
        //判斷是從TableRecyclerview還是ScheduleOfTheDay進入的
        if(bundle.getString("whichNumber").equals("3")) {
            presenter=new TablePresenter(this);

            tableManger=new LinearLayoutManager(this);
            bomIncuLayout.recyclerView.setLayoutManager(tableManger);

            titleIncuLayout.backtv.setText("進度表查詢");
            whichNumber=2;
            //要拿的Mo為第幾筆並setText
            position=bundle.getInt("position");
            setText();

            //從TableRecyclerview傳進所點擊資料的item_id
            presenter.gitItemId(listMo.get(position).getItem_id());

            //返回TableRecyclerview
            titleIncuLayout.backbt.setOnClickListener(view -> {
                Intent intent = new Intent(TableActivity.this, TableRecyclerviewActivity.class);
                startActivity(intent);
            });
        }
        else{
            whichNumber=2;
            titleIncuLayout.backtv.setText("當日進度表");

            //返回ScheduleOfTheDay
            titleIncuLayout.backbt.setOnClickListener(view -> {
                Intent intent = new Intent(TableActivity.this, MainMenuActivity.class);
                startActivity(intent);
            });
        }

        /**---------------radioGroup的換頁--------------------------*/
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            detailsLayout.removeAllViews();//清除detailsLayout
            switch (i) {
                case R.id.radioButton1:
                    detailsLayout.addView(detailsA);
                    nextPage.setVisibility(VisibilityAwareImageButton.VISIBLE);//顯示下一頁按鈕
                    previousPage.setVisibility(VisibilityAwareImageButton.INVISIBLE);//隱藏上一頁按鈕
                    whichNumber=1;
                    emptyText();
                    break;
                case R.id.radioButton2:
                    detailsLayout.addView(detailsA);
                    nextPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    previousPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    whichNumber=2;
                    if(listMo!=null)
                        setText();
                    break;
                case R.id.radioButton3:
                    detailsLayout.addView(detailsA);
                    nextPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    previousPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    whichNumber=3;
                    emptyText();
                    break;
                case R.id.radioButton4:
                    detailsLayout.addView(detailsA);
                    nextPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    previousPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    whichNumber=4;
                    emptyText();
                    break;
                case R.id.radioButton5:
                    detailsLayout.addView(detailsB);
                    nextPage.setVisibility(VisibilityAwareImageButton.INVISIBLE);//隱藏下一頁按鈕
                    previousPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
                    whichNumber=5;
                    break;
            }
        });
    }
    /**---------------按下"下一頁按鈕"--------------------------*/
    public void clickNextPage(View view){
        whichNumber+=1;
        if(whichNumber==5){
            nextPage.setVisibility(VisibilityAwareImageButton.INVISIBLE);
            radioButton5.setChecked(true);
        }
        else if(whichNumber==2) {
            radioButton2.setChecked(true);
            if(listMo!=null)
                setText();
        }
        else if(whichNumber==3){
            radioButton3.setChecked(true);
            emptyText();
        }
        else {
            radioButton4.setChecked(true);
            previousPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
            emptyText();
        }
    }
    /**---------------按下"上一頁按鈕"--------------------------*/
    public void clickPreviousPage(View view){
        whichNumber-=1;
        if(whichNumber==4){
            nextPage.setVisibility(VisibilityAwareImageButton.VISIBLE);
            detailsLayout.removeAllViews();
            detailsLayout.addView(detailsA);
            radioButton4.setChecked(true);
            emptyText();
        }
        else if(whichNumber==2) {
            radioButton2.setChecked(true);
            if(listMo!=null)
                setText();
        }
        else if(whichNumber==3) {
            radioButton3.setChecked(true);
            emptyText();
        }
        else {
            radioButton1.setChecked(true);
            previousPage.setVisibility(VisibilityAwareImageButton.INVISIBLE);
            emptyText();
        }
    }
    @Override
    public void takeBOM(List<TablePost> listBom) {
        this.listBom=listBom;
        tableAdapter=new TableRecyclerviewAdapter();
        bomIncuLayout.recyclerView.setAdapter(tableAdapter);
        tableAdapter.showBOM(listBom);
        bomIncuLayout.bomkey_name.setText(listBom.get(position).getParent().getBomkeyName());
    }
    //放入details資料
    private void setText(){
        DetailsIncuLayout detailsIncuLayout=new DetailsIncuLayout();
        ButterKnife.bind(detailsIncuLayout,this);
        detailsIncuLayout.mo_id_A.setText(listMo.get(position).getMo_id());
        detailsIncuLayout.so_id_A.setText(listMo.get(position).getSo_id());
        detailsIncuLayout.item_id_A.setText(listMo.get(position).getItem_id());
        detailsIncuLayout.item_name_A.setText(listMo.get(position).getItem_name());
        detailsIncuLayout.tech_routing_name_A.setText(listMo.get(position).getRelatedTechRoute().getTech_routing_name());
    }
    //清空details資料
    private void emptyText(){
        DetailsIncuLayout detailsIncuLayout=new DetailsIncuLayout();
        ButterKnife.bind(detailsIncuLayout,detailsA);
        detailsIncuLayout.mo_id_A.setText("");
        detailsIncuLayout.so_id_A.setText("");
        detailsIncuLayout.item_id_A.setText("");
        detailsIncuLayout.item_name_A.setText("");
        detailsIncuLayout.tech_routing_name_A.setText("");
    }
    public class TitleIncuLayout {
        @BindView(R.id.backtv) TextView backtv;
        @BindView(R.id.backBut) ImageButton backbt;
        @BindView(R.id.backUserName) TextView userName;
    }
    public class DetailsIncuLayout {
        @BindView(R.id.mo_id_A) TextView mo_id_A;
        @BindView(R.id.so_id_A) TextView so_id_A;
        @BindView(R.id.item_id_A) TextView item_id_A;
        @BindView(R.id.item_name_A) TextView item_name_A;
        @BindView(R.id.tech_routing_name_A) TextView tech_routing_name_A;
    }
    public  class BomIncuLayout{
        @BindView(R.id.tableRV) RecyclerView recyclerView;
        @BindView(R.id.bomkey_name) TextView bomkey_name;

    }

}