package com.example.flymperopoulos.jpmorganapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by flymperopoulos on 10/25/2014.
 */


public class MapFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mapview, container, false);

        Button backBtn = (Button)rootView.findViewById(R.id.backMap);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyActivity activity = (MyActivity) getActivity();
                activity.changeToMainPageFragment();
            }
        });

        return rootView;
    }
}
