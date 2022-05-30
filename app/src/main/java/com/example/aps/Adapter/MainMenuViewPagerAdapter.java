package com.example.aps.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.aps.Activity.MainMenuActivity;
import com.example.aps.Fragment.NewsletterFragment;
import com.example.aps.Fragment.ProductionScheduleFragment;
import com.example.aps.Fragment.ScheduleOfTheDayFragment;

public class MainMenuViewPagerAdapter extends FragmentStateAdapter {

    public MainMenuViewPagerAdapter(MainMenuActivity mainMenuActivity) {
        super(mainMenuActivity);
    }
    //Fragment滑動換頁
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case    0:
                return new ProductionScheduleFragment();
            case    1:
                return new ScheduleOfTheDayFragment();
            default:
                return new NewsletterFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
