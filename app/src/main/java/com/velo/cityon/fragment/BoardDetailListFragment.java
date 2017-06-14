package com.velo.cityon.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.velo.cityon.R;
import com.velo.cityon.adapter.BoardDetailListAdapter;
import com.velo.cityon.model.Posting;
import com.velo.cityon.service.PostingService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MACAIR on 2017. 6. 14..
 */

public class BoardDetailListFragment extends SwipeRefreshListFragment {

    final static String LOG_TAG = BoardDetailListFragment.class.getSimpleName();
    private String name;

    private List<Posting> items = new LinkedList<Posting>();

    public BoardDetailListFragment(){
    }

    public BoardDetailListFragment(String name){
        this.name = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, name +" : onCreate");

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, name +" : onViewCreated");


        if (savedInstanceState != null) {
            items = (List<Posting>) savedInstanceState.getSerializable("items");
        }else{
            items = new ArrayList<>();
        }



        BoardDetailListAdapter adapter = new BoardDetailListAdapter(
                getActivity(),
                R.layout.posting_list,
                items);

        setListAdapter(adapter);
        /*
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                initiateRefresh();
            }
        });
        */

        // END_INCLUDE (setup_refreshlistener)

        new BoardDetailListFragment.DummyBackgroundTask().execute();
    }

    @Override
    public void onStart() {
        Log.d(LOG_TAG, name +" : onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, name +" : onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, name +" : onPause");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        Log.d(LOG_TAG, name +" : onSaveInstanceState");
        super.onSaveInstanceState(outState);

        outState.putSerializable("items", (Serializable) items);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, name +" : onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(LOG_TAG, name +" : onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }

    private void onRefreshComplete(List<Posting> result) {
        Log.i(LOG_TAG, name +" : onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        ArrayAdapter<Posting> adapter = (ArrayAdapter<Posting>) getListAdapter();
        adapter.addAll(result);
        setRefreshing(false);
    }


    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<Posting>> {

        @Override
        protected List<Posting> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            List<Posting> list = new ArrayList<>();
            list.addAll(PostingService.getInstance().list());
            return list;
        }


        @Override
        protected void onPostExecute(List<Posting> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }
}
