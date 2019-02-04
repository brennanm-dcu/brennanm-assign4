package com.example.homer.rex;

/*
          Copyright (C) 2016 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.

*            Matthew Brennan SDA Assignment-4  MainActivity Feb 2019

*   This is my Assignment for a shopping website which sells T-Shirts designed for people
*   from the computer codeing community. It offers T-Shirts with set Logos and also offers
*   the facility for customers to send in a photo taken from their phone camera and have it
*   displayed on their T-Shirt.*
 */
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

// Main Activity Class
public class MainActivity extends AppCompatActivity {

        // LogCat Text
    private static final String  TAG = "MainActivirty";

    //Setting up variables
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

        // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // LogCat Text
        Log.d(TAG, "onCreate: Starting");

        //  Creates a SectionsPageAdapter object called mSectionPageAdapter
        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //The following sets up the ViewPager mViewPager and links it to the ViewPager container on activity_main
        mViewPager = (ViewPager) findViewById(R.id.container);
        // The following sends the mViewPager to the setUpViewPager method
        //there four fragments are associated with mViewPager.
        setUpViewPager(mViewPager);

        // The following sets up the tabLayout object and links it to tabs layout on activity_main
        // then associates the tabLayout with mViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    // setUpViewPager method uses SectionsPageAdapter  to add the Fragments in the list below, to
    // the viewPage  attribute.
    public void setUpViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_HomeFragment(), "HOME");
        adapter.addFragment(new Tab2_ProductsFragment(), "PRODUCTS");
        adapter.addFragment(new Tab3_OrdersFragment(), "ORDERS");
        adapter.addFragment(new Tab4_CollectionFragment(), "COLLECTION");
        viewPager.setAdapter(adapter);
    }
}
