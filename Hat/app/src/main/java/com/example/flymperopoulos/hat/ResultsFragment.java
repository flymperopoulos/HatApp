package com.example.flymperopoulos.hat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 10/22/14.
 */
public class ResultsFragment extends Fragment {

    public ResultsFragment(){}
    private Context context;

    Button backButton;
    ArrayList<String> resultsArray;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.results, container, false);

        backButton = (Button) rootView.findViewById(R.id.reset);

        resultsArray = ((MyActivity)getActivity()).resultsArray;
        ListView results = (ListView) rootView.findViewById(R.id.resultlist);

        ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, resultsArray);
        results.setAdapter(resultAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyActivity) getActivity()).changeToMainPageFragment();
            }


        });

        return rootView;
    }
}
