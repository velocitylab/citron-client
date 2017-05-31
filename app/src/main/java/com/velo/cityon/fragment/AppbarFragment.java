package com.velo.cityon.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.velo.cityon.R;
import com.velo.cityon.view.TitleScrollView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AppbarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AppbarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppbarFragment extends Fragment {
    private String TAG = AppbarFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    public AppbarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppbarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppbarFragment newInstance(String param1, String param2) {
        AppbarFragment fragment = new AppbarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appbar, container, false);

        final TitleScrollView scrollView = (TitleScrollView) view.findViewById(R.id.toolbar_title_scrollbar);
        scrollView.setSmoothScrollingEnabled(true);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if ( event.getAction() == MotionEvent.ACTION_UP ) {
                    scrollView.startScrollerTask();
                }
                return false;
            }
        });



        scrollView.setOnScrollStopListner(new TitleScrollView.onScrollStopListner() {
            @Override
            public void onScrollStoped() {
                // TODO Auto-generated method stub
                Log.d(TAG," "+scrollView.getScrollX());

            }

        });
/*
        final LinearLayout title = (LinearLayout)scrollView.findViewById(R.id.toolbar_title_layout);
        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,"OnTouch : " + String.valueOf(motionEvent.getAction()));
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    {
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    {
                        int x = (int)motionEvent.getX();
                        int y = (int)motionEvent.getY();


                        Log.d(TAG,"OnTouch : x : " + String.valueOf(x) + " Y :  " + String.valueOf(y));

                        Rect rect = title.getClipBounds();

                        for(int i = 0; i < title.getChildCount() ; i++)
                        {
                            TextView v = (TextView)title.getChildAt(i);

                            Rect r = v.getClipBounds();
                            if(r != null)
                            {
                                if(r.contains(x,y))
                                {
                                    scrollView.scrollTo(x - 100, y);
                                }
                            }
                            else
                            {
                                Log.d(TAG,"r is null");
                            }

                        }
                        break;
                    }
                    default:
                        break;
                }
                return true;
            }
        });
        */


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
}
