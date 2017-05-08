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

package cityon.velo.com.cityon.fragment.swiperefreshlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

import cityon.velo.com.cityon.R;
import cityon.velo.com.cityon.adapter.TestAdapter;
import cityon.velo.com.cityon.dummydata.Cheeses;


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

        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(false);
    }

    // BEGIN_INCLUDE (setup_views)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */

        TestAdapter adapter = new TestAdapter(
                getActivity(),
                R.layout.list,
                Cheeses.randomPersonList(10));

        // Set the adapter between the ListView and its backing data.
        setListAdapter(adapter);

        // BEGIN_INCLUDE (setup_refreshlistener)
        /**
         * Implement {@link SwipeRefreshLayout.OnRefreshListener}. When users do the "swipe to
         * refresh" gesture, SwipeRefreshLayout invokes
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}. In
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}, call a method that
         * refreshes the content. Call the same method in response to the Refresh action from the
         * action bar.
         */
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                initiateRefresh();
            }
        });
        // END_INCLUDE (setup_refreshlistener)
    }
    // END_INCLUDE (setup_views)


    // END_INCLUDE (setup_refresh_menu_listener)

    // BEGIN_INCLUDE (initiate_refresh)
    /**
     * By abstracting the refresh process to a single method, the app allows both the
     * SwipeGestureLayout onRefresh() method and the Refresh action item to refresh the content.
     */
    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        /**
         * Execute the background task, which uses {@link AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
    }
    // END_INCLUDE (initiate_refresh)

    // BEGIN_INCLUDE (refresh_complete)
    /**
     * When the AsyncTask finishes, it calls onRefreshComplete(), which updates the data in the
     * ListAdapter and turns off the progress bar.
     */
    private void onRefreshComplete(List<TestAdapter.Person> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        ArrayAdapter<TestAdapter.Person> adapter = (ArrayAdapter<TestAdapter.Person>) getListAdapter();
        //adapter.clear();
        for (TestAdapter.Person p : result) {
            adapter.add(p);
        }

        // Stop the refreshing indicator
        setRefreshing(false);
    }
    // END_INCLUDE (refresh_complete)

    /**
     * Dummy {@link AsyncTask} which simulates a long running task to fetch new cheeses.
     */
    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<TestAdapter.Person>> {

        static final int TASK_DURATION = 1 * 1000; // 1 seconds

        @Override
        protected List<TestAdapter.Person> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Cheeses.randomPersonList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<TestAdapter.Person> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }

}
