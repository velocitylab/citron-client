package com.velo.cityon.activity;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.velo.cityon.fragment.AppbarFragment;
import com.velo.cityon.fragment.BoardFragment;
import com.velo.cityon.fragment.BottomFragment;
import com.velo.cityon.fragment.MessageFragment;
import com.velo.cityon.fragment.ProfileFragment;
import com.velo.cityon.fragment.SearchFragment;
import com.velo.cityon.fragment.SwipeRefreshListFragmentFragment;

import com.velo.cityon.R;
import com.velo.cityon.fragment.WriteFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  BottomFragment.OnFragmentInteractionListener,
                                                                    AppbarFragment.OnFragmentInteractionListener,
                                                                    BoardFragment.OnFragmentInteractionListener,
                                                                    SearchFragment.OnFragmentInteractionListener,
                                                                    WriteFragment.OnFragmentInteractionListener,
                                                                    MessageFragment.OnFragmentInteractionListener,
                                                                    ProfileFragment.OnFragmentInteractionListener
{

    private Button buttonBoard;
    private Button buttonSearch;
    private Button buttonWrite;
    private Button buttonMessage;
    private Button buttonProfile;


    private BoardFragment boardFragment;
    private SearchFragment searchFragment;
    private WriteFragment writeFragment;
    private MessageFragment messageFragment;
    private ProfileFragment profileFragment;

    final private String FRAGMENT_TAG_BOARD = "board_fragment";
    final private String FRAGMENT_TAG_SEARCH = "search_fragment";
    final private String FRAGMENT_TAG_WRITE = "write_fragment";
    final private String FRAGMENT_TAG_MESSAGE = "message_fragment";
    final private String FRAGMENT_TAG_PROFILE = "profile_fragment";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            boardFragment = BoardFragment.newInstance();
            searchFragment = SearchFragment.newInstance();
            writeFragment = WriteFragment.newInstance();
            messageFragment = MessageFragment.newInstance();
            profileFragment = ProfileFragment.newInstance();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, boardFragment,FRAGMENT_TAG_BOARD)
                    .add(R.id.fragment_container, searchFragment,FRAGMENT_TAG_SEARCH)
                    .add(R.id.fragment_container, writeFragment,FRAGMENT_TAG_WRITE)
                    .add(R.id.fragment_container, messageFragment,FRAGMENT_TAG_MESSAGE)
                    .add(R.id.fragment_container, profileFragment,FRAGMENT_TAG_PROFILE)
                    .hide(searchFragment).hide(writeFragment).hide(messageFragment).hide(profileFragment)
                    .commit();
        }


        buttonBoard = (Button) findViewById(R.id.buttonBoard);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonWrite = (Button) findViewById(R.id.buttonWrite);
        buttonMessage = (Button) findViewById(R.id.buttonMessage);
        buttonProfile = (Button) findViewById(R.id.buttonProfile);

        buttonBoard.setOnClickListener(myCnClickListenr);
        buttonSearch.setOnClickListener(myCnClickListenr);
        buttonWrite.setOnClickListener(myCnClickListenr);
        buttonMessage.setOnClickListener(myCnClickListenr);
        buttonProfile.setOnClickListener(myCnClickListenr);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSetChangePageIndex(int index) {
        if(boardFragment != null)
            boardFragment.onSetChangePageIndex(index);

    }

    private View.OnClickListener myCnClickListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.buttonSearch:
                    getSupportFragmentManager().beginTransaction()
                            .show(searchFragment)
                            .hide(boardFragment).hide(writeFragment).hide(messageFragment).hide(profileFragment)
                            .commit();
                    break;
                case R.id.buttonWrite:
                    getSupportFragmentManager().beginTransaction()
                            .show(writeFragment)
                            .hide(boardFragment).hide(searchFragment).hide(messageFragment).hide(profileFragment)
                            .commit();
                    break;
                case R.id.buttonMessage:
                    getSupportFragmentManager().beginTransaction()
                            .show(messageFragment)
                            .hide(boardFragment).hide(writeFragment).hide(searchFragment).hide(profileFragment)
                            .commit();
                    break;
                case R.id.buttonProfile:
                    getSupportFragmentManager().beginTransaction()
                            .show(profileFragment)
                            .hide(boardFragment).hide(writeFragment).hide(messageFragment).hide(searchFragment)
                            .commit();
                    break;
                default:
                    getSupportFragmentManager().beginTransaction()
                            .show(boardFragment)
                            .hide(searchFragment).hide(writeFragment).hide(messageFragment).hide(profileFragment)
                            .commit();
                    break;

            }
        }
    };


    @Override
    public void onBackPressed() {
        if(boardFragment.isVisible())
            super.onBackPressed();
        else
            myCnClickListenr.onClick(buttonBoard);
    }

}
