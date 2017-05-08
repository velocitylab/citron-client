package com.velo.cityon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.velo.cityon.R;


public class LoadingActivity extends Activity {

    //private static final String TYPEFACE_NAME = "BM-JUA.ttf";
    //private Typeface typeface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                //Intent intent = new Intent(getApplicationContext(),ConfigActivity.class);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 500);
    }
}
