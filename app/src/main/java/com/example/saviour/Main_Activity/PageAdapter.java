package com.example.saviour.Main_Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.saviour.Home;

public class PageAdapter extends FragmentStatePagerAdapter {
    int tabcount;
    public PageAdapter(@NonNull FragmentManager fm,int behaviour) {
        super(fm,behaviour);
        tabcount=behaviour;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0 :return new Home();
            case 1 :return new Menu();
            case 2 :return new Other_Help();
            default:return null;

        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
