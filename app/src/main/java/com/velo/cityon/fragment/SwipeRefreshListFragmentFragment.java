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
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.velo.cityon.R;
import com.velo.cityon.adapter.TestAdapter;
import com.velo.cityon.dummydata.Cheeses;
import com.velo.cityon.model.Posting;

import java.util.ArrayList;
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

    private static final String LOG_TAG = SwipeRefreshListFragmentFragment.class.getSimpleName();

    private static final int LIST_ITEM_COUNT = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TestAdapter adapter = new TestAdapter(
                getActivity(),
                R.layout.posting_list,
                Cheeses.randomPersonList(1));

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

    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");
        new DummyBackgroundTask().execute();
    }


    private void onRefreshComplete(List<Posting> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        ArrayAdapter<Posting> adapter = (ArrayAdapter<Posting>) getListAdapter();
        //adapter.clear();

        for (Posting p : result) {
            adapter.add(p);
        }

        setRefreshing(false);
    }

    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<Posting>> {

        static final int TASK_DURATION = 1 * 1000; // 1 seconds

        @Override
        protected List<Posting> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random posting_list of cheeses
            return Cheeses.randomPersonList(3);
        }

        @Override
        protected void onPostExecute(List<Posting> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }

}
