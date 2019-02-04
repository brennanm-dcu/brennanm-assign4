package com.example.homer.rex;

//         Matthew Brennan SDA Assignment-4  MainActivity Feb 2019

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

//   FragmentStatePagerAdapter This creates an Array list of fragments
public class SectionsPageAdapter extends FragmentStatePagerAdapter {

    // Creates an list of Fragments
    private final List<Fragment> mFragmentList = new ArrayList<>();
    // Creates an list of Fragment names.
    private final List<String> mFragmentTitleList = new ArrayList<>();

    // The Following addFragment method adds fragments and fragmentNames
    // to their respective lists.
    public void addFragment (Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
     // SectionsPageAdapter constructor
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    // getter method for fragment
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
    @Override
    // getter method for fragment name
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    // getter method for fragment size
    public int getCount() {
        return mFragmentList.size();
    }
}
