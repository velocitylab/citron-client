package com.velo.cityon.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.velo.cityon.R;

import java.util.ArrayList;



public class TestAdapter extends ArrayAdapter<TestAdapter.Person> {

    private static final String LOG_TAG = TestAdapter.class.getSimpleName();

    private ArrayList<Person> items;

    public TestAdapter(Context context, int textViewResourceId, ArrayList<Person> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
           LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = vi.inflate(R.layout.list, null);
        }

        Log.d(LOG_TAG, "position : "+position);
        Person p = items.get(position);

        if (p != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView mt = (TextView) v.findViewById(R.id.middletext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null){
                tt.setText(p.getName());
            }
            if(mt != null){
                mt.setText("number : "+ p.getNumber());
            }
            if(bt != null){
                bt.setText("count : "+ position);
            }
        }
        return v;
    }

    public static class Person{
        private String Name;
        private String Number;

        public Person(String _Name, String _Number){
            this.Name = _Name;
            this.Number = _Number;
        }

        public String getName() {
            return Name;
        }

        public String getNumber() {
            return Number;
        }
    }
}


