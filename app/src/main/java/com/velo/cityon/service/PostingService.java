package com.velo.cityon.service;

import android.util.Log;

import com.velo.cityon.adapter.TestAdapter;
import com.velo.cityon.api.PostingInterface;
import com.velo.cityon.common.Constants;
import com.velo.cityon.common.ResultCode;
import com.velo.cityon.model.Posting;
import com.velo.cityon.model.PostingVO;
import com.velo.cityon.utils.HttpUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by jhspi on 2017-06-06.
 */

public class PostingService {

    private static final String LOG_TAG = PostingService.class.getSimpleName();


    private static PostingService instance;

    private PostingInterface postingInterface;

    public static PostingService getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new PostingService();
        return instance;
    }

    private PostingService(){
        postingInterface = HttpUtil.getInstance(Constants.VELO_URL).create(PostingInterface.class);
    }

    public List<Posting> list() {

        List<Posting> result = new LinkedList<>();
        try {
            Response<PostingVO> resp= postingInterface.list().execute();
            if(resp.isSuccessful() && resp.body() != null && ResultCode.SUCESS.equals(resp.body().result)){
                result = resp.body().posts;
                for(Posting p : result){
                    Log.d(LOG_TAG, p.toString());
                }
            }else{
                Log.d(LOG_TAG, "error !!!!!!!!!!!!!!!!");
                Log.d(LOG_TAG, "isSuccessful : " + resp.isSuccessful());
                Log.d(LOG_TAG, "resp.body()  : " + resp.body());
                Log.d(LOG_TAG, "resp.body.result : " + resp.body().result);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
