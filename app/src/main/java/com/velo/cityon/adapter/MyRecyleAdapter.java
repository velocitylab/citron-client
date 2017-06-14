package com.velo.cityon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.velo.cityon.R;
import com.velo.cityon.model.SearchHistory;

import java.util.List;

public class MyRecyleAdapter extends RecyclerView.Adapter<MyRecyleAdapter.ViewHolder> {

    private static final String LOG_TAG = TestAdapter.class.getSimpleName();

    private List<SearchHistory> searchHistoryList;
    private int itemLayout;

    public MyRecyleAdapter(List<SearchHistory> items , int itemLayout){
        this.searchHistoryList = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        SearchHistory item = searchHistoryList.get(position);
        viewHolder.textTitle.setText(item.getTitle());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "remove : "+position);
                searchHistoryList.remove(position);
                notifyDataSetChanged();
            }
        };
        viewHolder.imageButton.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return searchHistoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textTitle;
        public ImageButton imageButton;

        public ViewHolder(View itemView){
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.search_text);
            imageButton = (ImageButton) itemView.findViewById(R.id.search_image_button);
        }

    }
}

