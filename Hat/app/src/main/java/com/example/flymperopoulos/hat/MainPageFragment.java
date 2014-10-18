package com.example.flymperopoulos.hat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by flymperopoulos on 10/18/2014.
 */
public class MainPageFragment extends Fragment {

    private Context context;
    public MainPageFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainmenu, container, false);

        Log.d("Debug", "does this show up?");
        return rootView;
    }
    }

