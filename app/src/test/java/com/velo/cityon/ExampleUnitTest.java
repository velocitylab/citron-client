package com.velo.cityon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.velo.cityon.api.PostingInterface;
import com.velo.cityon.model.Posting;
import com.velo.cityon.model.PostingVO;
import com.velo.cityon.utils.HttpUtil;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testHttpUtil() throws IOException {

        HttpUtil httpUtil = HttpUtil.getInstance("http://220.230.113.103/");;

        Response<PostingVO> result2 = httpUtil.create(PostingInterface.class).list().execute();

        System.out.println("isSuccessful : "+result2.isSuccessful());
        PostingVO postingVO = result2.body();
        System.out.println(postingVO.result);
        System.out.println(postingVO.message);

        if(postingVO.posts == null){
            System.out.println("list is null");
        }else{
            for(Posting p : postingVO.posts){
                System.out.println(p.toString());
            }
        }



    }




    public void addition_isCorrect() throws Exception {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://220.230.113.103/")
                .addConverterFactory(
                        GsonConverterFactory.create(gson))
                .build();

        PostingInterface service = retrofit.create(PostingInterface.class);
        Call<PostingVO> result = service.list();
        Response<PostingVO> result2 = result.execute();

        System.out.println("isSuccessful : "+result2.isSuccessful());
        PostingVO postingVO = result2.body();
        System.out.println(postingVO.result);
        System.out.println(postingVO.message);

        if(postingVO.posts == null){
            System.out.println("list is null");
        }else{
            for(Posting p : postingVO.posts){
                System.out.println(p.toString());
            }
        }
    }
}