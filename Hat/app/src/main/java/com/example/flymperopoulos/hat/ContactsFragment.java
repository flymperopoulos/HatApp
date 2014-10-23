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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by james on 10/21/14.
 */
public class ContactsFragment extends Fragment {

    public ContactsFragment(){}
    private Context context;
    Button sendButton;
    Button arrowButton;
    String allnames;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contactspage, container, false);
        ListView contacts = (ListView) rootView.findViewById(R.id.contactlist);
        final ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), readContacts());
        contacts.setAdapter(contactsAdapter);

        SideBar indexBar = (SideBar) rootView.findViewById(R.id.sideBar);
        indexBar.setListView(contacts);

        sendButton = (Button) rootView.findViewById(R.id.submitbutton);
        arrowButton = (Button) rootView.findViewById(R.id.sendbutton);

        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String chosen = contactsAdapter.getItem(i).toString();
//                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
//                checkBox.setVisibility(View.VISIBLE);
                sendButton.setVisibility(View.VISIBLE);
                arrowButton.setVisibility(View.VISIBLE);

                if (allnames != null) {
                    List<String> nameList = new ArrayList<String>(Arrays.asList(allnames.split(", ")));
                    if (nameList.contains(chosen)) {
                        nameList.remove(chosen);
                        if (nameList.isEmpty()){
                            sendButton.setVisibility(View.INVISIBLE);
                            arrowButton.setVisibility(View.INVISIBLE);
                        }
                        String temp = "";
                        for (int j=0; j<nameList.size(); j++){
                            temp += nameList.get(j) + ", ";
                        }
                        allnames = temp ;
                    } else {
                        allnames += chosen + ", ";
                    }
                } else {
                    allnames = chosen + ", ";
                }
                sendButton.setText(allnames);
            }
        });

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyActivity) getActivity()).changeToResultsFragment();
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
            String s = contactName;
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
