package com.example.mvvmtestapp3.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.DnsResolver;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmtestapp3.R;
import com.example.mvvmtestapp3.adapter.FlickAdapter;
import com.example.mvvmtestapp3.data.FlickBuilder;
import com.example.mvvmtestapp3.databinding.ActivityMainBinding;
import com.example.mvvmtestapp3.model.FlickrPhoto;
import com.example.mvvmtestapp3.model.Photo;
import com.example.mvvmtestapp3.model.Photos;
import com.example.mvvmtestapp3.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private FlickAdapter flickAdapter;
    private static final int NUM_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.rvFlickPhoto;

        StaggeredGridLayoutManager staggeredGridLayoutManager = new
                StaggeredGridLayoutManager(NUM_COLUMNS,LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        flickAdapter = new FlickAdapter(new FlickAdapter.FlickAdapterListener() {
            @Override
            public void onFlickClicked(Photo photo) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("position",photo);
                startActivity(intent);
            }
        }, getApplicationContext());
        recyclerView.setAdapter(flickAdapter);
        getAllPhoto();

    }

    private void getAllPhoto() {
        mainViewModel.getAllFlickPhoto().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(@Nullable List<Photo> photos) {
                flickAdapter.setFlickPhotoList((ArrayList<Photo>) photos);
            }
        });
    }
}