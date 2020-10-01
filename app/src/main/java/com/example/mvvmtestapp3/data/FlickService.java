package com.example.mvvmtestapp3.data;

import com.example.mvvmtestapp3.model.FlickrPhoto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickService {
    @GET("services/rest")
    Call<FlickrPhoto> fetchFlick(@Query("extras") String extras,
                                 @Query("nojsoncallback") String nojsoncallback,
                                 @Query("user_id") String user_id,
                                 @Query("format") String format,
                                 @Query("api_key") String api_key,
                                 @Query("method") String method,
                                 @Query("page") int page,
                                 @Query("per_page") int per_page);
}
