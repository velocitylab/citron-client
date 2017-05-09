package com.velo.cityon.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.velo.cityon.R;
import com.velo.cityon.adapter.TestAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String LOG_TAG = BoardFragment.class.getSimpleName();


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FragmentManager fragmentManager;

    private ViewPager mViewPager;
    private OnFragmentInteractionListener mListener;

    private LinearLayout mTitleList;
    private LinearLayout mTitleIndexer;

    // TODO: Rename and change types and number of parameters
    public static BoardFragment newInstance() {
        BoardFragment fragment = new BoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //--------------
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mTitleList = (LinearLayout) view.findViewById(R.id.toolbar_title_layout);
        mTitleIndexer = (LinearLayout) view.findViewById(R.id.toolbar_title_page_indexer);


        if (mViewPager != null) {
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                StringBuilder title = new StringBuilder();
                title.append(mViewPager.getAdapter().getPageTitle(i));

                TextView text = new TextView(getContext());
                text.setText(title);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setTextSize(30);
                text.setPadding(10, 0, 10, 0);
                mTitleList.addView(text);

                ImageView indexer = new ImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(5, 0, 5, 0);
                indexer.setLayoutParams(lp);
                indexer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_toolbar_index_select_shape));
                mTitleIndexer.addView(indexer);
            }
        }
        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new SwipeRefreshListFragmentFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION" + position;
        }
    }
}
