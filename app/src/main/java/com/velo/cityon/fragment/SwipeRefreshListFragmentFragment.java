/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.velo.cityon.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.velo.cityon.R;
import com.velo.cityon.adapter.PostingAdapter;
import com.velo.cityon.adapter.TestAdapter;
import com.velo.cityon.dummydata.Cheeses;
import com.velo.cityon.model.Posting;
import com.velo.cityon.service.PostingService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * A sample which shows how to use {@link android.support.v4.widget.SwipeRefreshLayout} within a
 * {@link android.support.v4.app.ListFragment} to add the 'swipe-to-refresh' gesture to a
 * {@link android.widget.ListView}. This is provided through the provided re-usable
 * {@link SwipeRefreshListFragment} class.
 *
 * <p>To provide an accessible way to trigger the refresh, this app also provides a refresh
 * action item. This item should be displayed in the Action Bar's overflow item.
 *
 * <p>In this sample app, the refresh updates the ListView with a random set of new items.
 *
 * <p>This sample also provides the functionality to change the colors displayed in the
 * {@link android.support.v4.widget.SwipeRefreshLayout} through the options menu. This is meant to
 * showcase the use of color rather than being something that should be integrated into apps.
 */
public class SwipeRefreshListFragmentFragment extends SwipeRefreshListFragment {

    private String name;

    private SwipeRefreshListFragmentFragment(){
    }

    public SwipeRefreshListFragmentFragment(String name){
        this.name = name;
    }

    private static final String LOG_TAG = SwipeRefreshListFragmentFragment.class.getSimpleName();
    private List<Posting> items = new LinkedList<Posting>();

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
//        TestAdapter adapter = new TestAdapter(
//                getActivity(),
//                R.layout.posting_list,
//                items);

        PostingAdapter adapter = new PostingAdapter(
                getActivity(),
                R.layout.posting_list,
                items);

        setListAdapter(adapter);
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                initiateRefresh();
            }
        });
        // END_INCLUDE (setup_refreshlistener)
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

    private void initiateRefresh() {
        Log.i(LOG_TAG, name +"initiateRefresh");
        new DummyBackgroundTask().execute();
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
