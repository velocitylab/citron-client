package com.velo.cityon.utils;

import android.util.Log;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jhspi on 2017-05-18.
 */

public class HttpUtil {

    public void test() throws Exception{
        // Create URL
        URL githubEndpoint = new URL("http://175.158.15.84");

        // Create connection
        HttpsURLConnection conn =  (HttpsURLConnection) githubEndpoint.openConnection();

        int responseCode = conn.getResponseCode();
        String responseMessage = conn.getResponseMessage();

        Log.d("TAG", "responseCode    : "+responseCode);
        Log.d("TAG", "responseMessage : "+responseMessage);
    }

    public void test2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

}
