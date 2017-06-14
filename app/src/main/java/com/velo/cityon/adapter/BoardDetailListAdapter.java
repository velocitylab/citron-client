package com.velo.cityon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.velo.cityon.R;
import com.velo.cityon.model.Posting;

import java.util.List;

/**
 * Created by MACAIR on 2017. 6. 14..
 */

public class BoardDetailListAdapter extends ArrayAdapter<Posting> {

    private static final String LOG_TAG = TestAdapter.class.getSimpleName();

    private static final int SPINNER = 0;
    private static final int POSTING = 1;

    private List<Posting> items;


    public BoardDetailListAdapter(Context context, int textViewResourceId, List<Posting> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);


        if (type == SPINNER) {

            BoardDetailListAdapter.ViewHolderSpinner viewHolderSpinner;
            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.posting_spinner, null);
                Spinner spinner = (Spinner) convertView.findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), "click : "+position, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                viewHolderSpinner = new BoardDetailListAdapter.ViewHolderSpinner();
                viewHolderSpinner.spinner = spinner;
                convertView.setTag(viewHolderSpinner);
            }
            else {
                viewHolderSpinner = (BoardDetailListAdapter.ViewHolderSpinner) convertView.getTag();
            }
        }
        else {
            BoardDetailListAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.posting_list, null);

                TextView pTitle      = (TextView) convertView.findViewById(R.id.posting_title);
                TextView pWriter     = (TextView) convertView.findViewById(R.id.posting_writer);
                TextView pDate = (TextView) convertView.findViewById(R.id.posting_date);
                TextView pHitCount   = (TextView) convertView.findViewById(R.id.posting_hit_count);
                TextView pLikeCount  = (TextView) convertView.findViewById(R.id.posting_like_count);
                TextView pReplyCount = (TextView) convertView.findViewById(R.id.posting_reply_count);

                viewHolder = new BoardDetailListAdapter.ViewHolder();
                viewHolder.pTitle = pTitle;
                viewHolder.pWriter = pWriter;
                viewHolder.pDate = pDate;
                viewHolder.pHitCount = pHitCount;
                viewHolder.pLikeCount = pLikeCount;
                viewHolder.pReplyCount = pReplyCount;

                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (BoardDetailListAdapter.ViewHolder) convertView.getTag();
            }
            Posting p = items.get(position);
            viewHolder.pTitle.setText(p.getTitle());
            viewHolder.pWriter.setText(p.getWriter());
            viewHolder.pDate.setText(p.getDate().toString());
            viewHolder.pHitCount.setText(String.valueOf(p.getHitCount()));
            viewHolder.pLikeCount.setText(String.valueOf(p.getLikeCount()));
            viewHolder.pReplyCount.setText(String.valueOf(p.getReplyCount()));
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SPINNER;
        }
        else {
            return POSTING;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    protected class ViewHolderSpinner {
        private Spinner spinner;
    }

    protected class ViewHolder {
        private TextView pTitle;
        private TextView pWriter;
        private TextView pDate;
        private TextView pHitCount;
        private TextView pLikeCount;
        private TextView pReplyCount;
    }
}
