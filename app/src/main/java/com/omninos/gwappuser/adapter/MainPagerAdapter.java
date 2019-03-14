package com.omninos.gwappuser.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.omninos.gwappuser.fragments.JobFragment;
import com.omninos.gwappuser.fragments.ServicesFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    int noOfTabs;

    public MainPagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ServicesFragment servicesFragment=new ServicesFragment();
                return servicesFragment;
            case 1:
                JobFragment jobFragment=new JobFragment();
                return jobFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}