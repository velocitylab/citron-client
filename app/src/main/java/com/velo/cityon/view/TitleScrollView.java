package com.velo.cityon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by MACAIR on 2017. 5. 31..
 */

public class TitleScrollView extends HorizontalScrollView {
    private Runnable scrollerTask;
    private int intitPosition;
    private int newCheck = 100;
    private static final String TAG = "ScrollView";

    public interface onScrollStopListner{
        void onScrollStoped();
    }

    private onScrollStopListner onScrollstopListner;

    public TitleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        scrollerTask = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                int newPosition = getScrollX();
                if ( intitPosition - newPosition == 0 ) {
                    if ( onScrollstopListner != null ) {
                        onScrollstopListner.onScrollStoped();
                    }
                }else{
                    intitPosition = getScrollX();
                    TitleScrollView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };
    }

    public void setOnScrollStopListner( TitleScrollView.onScrollStopListner listner ){
        onScrollstopListner = listner;
    }

    public void startScrollerTask(){
        intitPosition = getScrollX();
        TitleScrollView.this.postDelayed(scrollerTask, newCheck);
    }
}
