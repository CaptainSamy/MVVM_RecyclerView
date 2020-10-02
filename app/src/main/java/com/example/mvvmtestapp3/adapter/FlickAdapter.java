package com.example.mvvmtestapp3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtestapp3.R;
import com.example.mvvmtestapp3.databinding.ItemFlickPhotoBinding;
import com.example.mvvmtestapp3.model.Photo;

import java.util.ArrayList;

public class FlickAdapter extends RecyclerView.Adapter<FlickAdapter.FlickViewHolder> {
    private ArrayList<Photo> photoArrayList;
    private FlickAdapterListener flickAdapterListener;
    private Context context;

    public FlickAdapter(FlickAdapterListener flickAdapterListener, Context context) {
        this.flickAdapterListener = flickAdapterListener;
        this.context = context;
    }


    @NonNull
    @Override
    public FlickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFlickPhotoBinding itemFlickPhotoBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_flick_photo, parent, false);
        return new FlickViewHolder(itemFlickPhotoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickViewHolder holder, int position) {
        Photo currentPhoto = photoArrayList.get(position);
        holder.itemFlickPhotoBinding.setPhoto(currentPhoto);

        //click item
        holder.itemFlickPhotoBinding.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flickAdapterListener != null) {
                    flickAdapterListener.onFlickClicked(photoArrayList.get(position));
                    Toast.makeText(context, "Click" + position, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (photoArrayList != null) {
            return photoArrayList.size();
        } else {
            return 0;
        }
    }

    public void setFlickPhotoList(ArrayList<Photo> photos) {
        this.photoArrayList = photos;
        notifyDataSetChanged();
    }

    public class FlickViewHolder extends RecyclerView.ViewHolder {
        private ItemFlickPhotoBinding itemFlickPhotoBinding;

        public FlickViewHolder(@NonNull ItemFlickPhotoBinding itemFlickPhotoBinding) {
            super(itemFlickPhotoBinding.getRoot());
            this.itemFlickPhotoBinding = itemFlickPhotoBinding;
        }
    }

    public interface FlickAdapterListener {
        void onFlickClicked(Photo photo);
    }
}
