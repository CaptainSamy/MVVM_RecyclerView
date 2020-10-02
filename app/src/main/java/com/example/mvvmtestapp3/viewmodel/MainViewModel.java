package com.example.mvvmtestapp3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmtestapp3.data.FlickRepository;
import com.example.mvvmtestapp3.model.Photo;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private FlickRepository flickRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        flickRepository = new FlickRepository();
    }

    public LiveData<List<Photo>> getAllFlickPhoto() {
        return flickRepository.getMutableLiveData();
    }
}
