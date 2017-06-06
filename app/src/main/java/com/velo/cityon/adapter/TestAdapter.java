package com.velo.cityon.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.velo.cityon.R;
import com.velo.cityon.model.Posting;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends ArrayAdapter<Posting> {

    private static final String LOG_TAG = TestAdapter.class.getSimpleName();

    private List<Posting> items;

    public TestAdapter(Context context, int textViewResourceId, List<Posting> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
           LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = vi.inflate(R.layout.posting_list, null);
        }

        Log.d(LOG_TAG, "position : "+position);
        Posting p = items.get(position);

        if (p != null) {
            TextView pTitle      = (TextView) v.findViewById(R.id.posting_title);
            TextView pWriter     = (TextView) v.findViewById(R.id.posting_writer);
            TextView pHitCount   = (TextView) v.findViewById(R.id.posting_hit_count);
            TextView pLikeCount  = (TextView) v.findViewById(R.id.posting_like_count);
            TextView pReplyCount = (TextView) v.findViewById(R.id.posting_reply_count);

            pTitle.setText(p.getTitle());
            pWriter.setText(p.getWriter());
            pHitCount.setText(String.valueOf(p.getHitCount()));
            pLikeCount.setText(String.valueOf(p.getLikeCount()));
            pReplyCount.setText(String.valueOf(p.getReplyCount()));
        }
        return v;
    }
}


