package com.example.flymperopoulos.hat;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by james on 10/21/14.
 */
public class ContactsFragment extends Fragment {

    public ContactsFragment(){}
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contactspage, container, false);

        return rootView;

    }
}
