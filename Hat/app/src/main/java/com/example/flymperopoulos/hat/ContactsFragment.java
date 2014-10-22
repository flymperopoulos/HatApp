package com.example.flymperopoulos.hat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 10/21/14.
 */
public class ContactsFragment extends Fragment {

    public ContactsFragment(){}
    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.contactspage, container, false);
        ListView contacts = (ListView) rootView.findViewById(R.id.contactlist);
        final ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), readContacts());
        contacts.setAdapter(contactsAdapter);

        SideBar indexBar = (SideBar) rootView.findViewById(R.id.sideBar);
        indexBar.setListView(contacts);

        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contactsAdapter.getItem(i).toString();
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                checkBox.setVisibility(View.VISIBLE);
            }
        });

        return rootView;

    }

    public ArrayList<String> readContacts() {
        Log.d("START", "Getting all Contacts");
        ArrayList<String> arrContacts = new ArrayList<String>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(uri,
                new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone._ID},
                null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String contactNumber= cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactName =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contactNumber = contactNumber.replaceAll("[^[0-9]*$]", "");
            if(contactNumber.length()>10){
                contactNumber = contactNumber.substring(1);
            }
            String s = contactName + " " + contactNumber;
            if (s != null)
            {
                arrContacts.add(s);
            }
            cursor.moveToNext();
        }
        cursor.close();
        cursor = null;
        Log.d("END","Got all Contacts");
        return arrContacts;
    }
}
