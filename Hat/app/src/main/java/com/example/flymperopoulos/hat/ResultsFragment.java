package com.example.flymperopoulos.hat;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by james on 10/22/14.
 */
public class ResultsFragment extends Fragment {

    public ResultsFragment(){}
    private Context context;

    Button backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.results, container, false);

        backButton = (Button) rootView.findViewById(R.id.reset);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyActivity) getActivity()).changeToMainPageFragment();
            }
        });

        return rootView;
    }
}
