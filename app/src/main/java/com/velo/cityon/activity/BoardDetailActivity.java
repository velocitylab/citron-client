package com.velo.cityon.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.velo.cityon.R;
import com.velo.cityon.fragment.BoardDetailListFragment;

public class BoardDetailActivity extends AppCompatActivity {

    final static private String TAG = BoardDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        if(position != -1){
            Log.d(TAG,"select Position : " + position);
        }
        else {
            Log.d(TAG,"select position error ");
        }

        if(getFragmentManager().findFragmentById(R.id.fragment_list) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_list, new BoardDetailListFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


