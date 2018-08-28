package com.muliamaulana.denius.api;

import com.muliamaulana.denius.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by muliamaulana on 8/18/2018.
 */

public class ClientAPI {
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static Retrofit retrofit = null;

    public static Retrofit getNewsAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
