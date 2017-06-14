package com.velo.cityon.api;

import com.velo.cityon.model.PostingVO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jhspi on 2017-06-06.
 */

public interface PostingInterface {

    @GET("v1/post/_list")
    Call<PostingVO> list();

}
