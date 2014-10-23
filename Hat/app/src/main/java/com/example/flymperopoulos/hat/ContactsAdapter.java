package com.example.flymperopoulos.hat;

import android.content.Context;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 10/22/14.
 */
public class ContactsAdapter extends BaseAdapter implements SectionIndexer {
    private List<String> contacts = new ArrayList<String>();
    private Context context;


    public ContactsAdapter(Context context,List<String> objects) {
        this.context = context;
        this.contacts = objects;

    }

    private class ContactsHolder {
        TextView name;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.contacts, null);
        LinearLayout header = (LinearLayout) view.findViewById(R.id.section);
        String label = contacts.get(position);
        char firstChar = label.toUpperCase().charAt(0);
        if (position == 0) {
            setSection(header, label);
        } else {
            String preLabel = contacts.get(position - 1);
            char preFirstChar = preLabel.toUpperCase().charAt(0);
            if (firstChar != preFirstChar) {
                setSection(header, label);
            } else {
                header.setVisibility(View.GONE);
            }
        }
        TextView textView = (TextView) view.findViewById(R.id.contact_info);
        textView.setText(label);
        return view;
    }
    @Override
    public Object getItem(int arg0) {
        return contacts.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public int getCount(){
        return this.contacts.size();
    }


    public void addContacts(String s){
        this.contacts.add(s);
        notifyDataSetChanged();
    }
    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getSectionForPosition(int arg0) {
        return 0;
    }
    private void setSection(LinearLayout header, String label) {
        TextView text = new TextView(context);
        header.setBackgroundColor(0xffaabbcc);
        text.setTextColor(Color.WHITE);
        text.setText(label.substring(0, 1).toUpperCase());
        text.setTextSize(20);
        text.setPadding(5, 0, 0, 0);
        text.setGravity(Gravity.CENTER_VERTICAL);
        header.addView(text);
    }
    @Override
    public int getPositionForSection(int section) {
        if (section == 35) {
            return 0;
        }
        for (int i = 0; i < contacts.size(); i++) {
            String l = contacts.get(i);
            char firstChar = l.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
