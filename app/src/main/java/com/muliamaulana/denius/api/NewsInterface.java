package com.muliamaulana.denius.api;

import com.muliamaulana.denius.model.Headlines;
import com.muliamaulana.denius.model.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by muliamaulana on 8/18/2018.
 */

public interface NewsInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines (@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<Headlines> getCategory (@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);

    @GET("sources")
    Call<Sources> getSources (@Query("apiKey") String apikey);

    @GET("top-headlines")
    Call<Headlines> getHeadlinesSource (@Query("sources") String sources,@Query("apiKey") String apiKey);

    @GET("everything")
    Call<Headlines> getEverythingSource (@Query("sources") String sources,@Query("apiKey") String apiKey);
}
