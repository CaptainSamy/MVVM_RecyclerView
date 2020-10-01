package com.example.mvvmtestapp3.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickBuilder {
    private static final String BASE_URL = "https://www.flickr.com";
    public static FlickService service;

    public static FlickService create() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(FlickService.class);
        }
        return service;
    }
}
