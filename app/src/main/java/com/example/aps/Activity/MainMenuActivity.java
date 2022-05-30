package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.example.aps.Adapter.MainMenuViewPagerAdapter;
import com.example.aps.R;
import com.example.aps.TokenAndName;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity {
    @BindView(R.id.viewpeger) ViewPager2 viewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.backUserName) TextView userName;
    private String[] titles=new String[]{"生產排程","當日進度表","通訊通知"};
    private MainMenuViewPagerAdapter mainMenuViewPagerAdapter;
    private TokenAndName tokenAndName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        //拿取使用著名稱
        tokenAndName = new TokenAndName((Context) this);
        userName.setText(tokenAndName.takeName());

        //設定viewPagerAdapter
        mainMenuViewPagerAdapter =new MainMenuViewPagerAdapter(this);
        viewPager.setAdapter(mainMenuViewPagerAdapter);

        //tabLayout與viewPager2的滑動和點選連動
        new TabLayoutMediator(tabLayout, viewPager,true,false,
                (tab, position) -> tab.setText(titles[position])).attach();
        tabLayout.selectTab(tabLayout.getTabAt(1));
    }
}