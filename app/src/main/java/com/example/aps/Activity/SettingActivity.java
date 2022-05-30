package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.aps.R;
import butterknife.BindView;
import butterknife.ButterKnife;
public class SettingActivity extends AppCompatActivity {
    //先取的Include
    @BindView(R.id.settingIncu) View settingIncu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //先綁定Include
        ButterKnife.bind(this);
        //建立includedLayout的class
        IncludeLayout includedLayout = new IncludeLayout();
        //把class和ButterKnife綁定
        ButterKnife.bind(includedLayout,settingIncu);

        includedLayout.settingBacktv.setText("設定");
        //按backBt返回Main
        includedLayout.settingBackbut.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    public class IncludeLayout {
        @BindView(R.id.backBut) ImageButton settingBackbut;
        @BindView(R.id.backtv) TextView settingBacktv;
    }
}