package com.example.homer.rex;

//         Matthew Brennan SDA Assignment-4  Tab1_HomeFragment Feb 2019

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Tab1_HomeFragment Class
public class Tab1_HomeFragment extends Fragment {
    private static final String TAG = "Tab1_HomeFragment";
    @Nullable
    @Override
    // onCreate tab1_home layout is inflated
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab1_home, container, false);
        Log.d(TAG, "Tab1_HomeFragment");
        return view;
    }
}
