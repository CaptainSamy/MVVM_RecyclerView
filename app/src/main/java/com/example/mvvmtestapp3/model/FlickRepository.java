package com.example.mvvmtestapp3.model;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtestapp3.data.FlickBuilder;
import com.example.mvvmtestapp3.data.FlickService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickRepository {
    private List<Photo> photoList = new ArrayList<>();
    private MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();

    private static final String FULL_EXTRAS = "views, media, path_alias, url_l, url_o";
    private static final String USERS_ID = "186424648@N06";
    private static final String KEY_TOKEN = "7a4b5ef02077a1f5dd3f1fef0d14ecb6";
    private static final String GET_FAVO = "flickr.favorites.getList";
    private int PAGE = 1;

    public FlickRepository() {
    }

    public MutableLiveData<List<Photo>> getMutableLiveData() {
        final FlickService flickService = FlickBuilder.create();

        Call<FlickrPhoto> call = flickService.fetchFlick(FULL_EXTRAS,"1", USERS_ID, "json", KEY_TOKEN, GET_FAVO, PAGE, 10);
        call.enqueue(new Callback<FlickrPhoto>() {
            @Override
            public void onResponse(Call<FlickrPhoto> call, Response<FlickrPhoto> response) {
                FlickrPhoto flickrPhoto = response.body();
                Photos photos = flickrPhoto.getPhotos();
                photoList = photos.getPhoto();
                mutableLiveData.setValue(photoList);
            }

            @Override
            public void onFailure(Call<FlickrPhoto> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
