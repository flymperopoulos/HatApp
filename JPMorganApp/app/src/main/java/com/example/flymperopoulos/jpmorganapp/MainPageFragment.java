package com.example.flymperopoulos.jpmorganapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by flymperopoulos on 10/25/2014.
 */
public class MainPageFragment extends Fragment {

    private Context context;
    public MainPageFragment() {}
    Button map;
    ListView listview;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mainmenu, container, false);
        map = (Button) rootView.findViewById(R.id.map_button);

        listview = (ListView) rootView.findViewById(R.id.requestlist);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyActivity) getActivity()).changeToMap();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                MyActivity activity = (MyActivity) getActivity();
                activity.changeToMainPageFragment();
            }
        });
        return rootView;
    }
}