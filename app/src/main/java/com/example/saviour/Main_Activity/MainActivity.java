package com.example.saviour.Main_Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.saviour.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TabLayout tablayout;
    TabItem tabitem1,tabitem2,tabitem3;
    ViewPager viewpager1;
    PageAdapter pageadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tablayout= findViewById(R.id.tablayout1);
        tabitem1= findViewById(R.id.tabitem1);
        tabitem2= findViewById(R.id.tabitem2);
        tabitem3= findViewById(R.id.tabitem3);
        viewpager1= findViewById(R.id.viewpager1);

        pageadapter = new PageAdapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager1.setAdapter(pageadapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
             viewpager1.setCurrentItem(tab.getPosition());

             if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2)
             {
                 pageadapter.notifyDataSetChanged();
             }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }
}