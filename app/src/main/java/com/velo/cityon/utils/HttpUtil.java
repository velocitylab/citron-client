package com.velo.cityon.utils;

import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jhspi on 2017-05-18.
 */

public class HttpUtil {

    private Retrofit retrofit;

    static private Map<String, HttpUtil> cache = new HashMap<String, HttpUtil>();

    public static HttpUtil getInstance(String baseUrl) {
        HttpUtil httpUtil = cache.get(baseUrl);
        if (httpUtil == null) {
            httpUtil  = new HttpUtil(baseUrl);
            cache.put(baseUrl, httpUtil);
        }
        return httpUtil;
    }

    private HttpUtil(String baseUrl){
        this(baseUrl, GsonConverterFactory.create(new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()));
    }

    private HttpUtil(String baseUrl, Converter.Factory factory){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
