package com.example.lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4.R;
import com.example.lab4.model.Photo;

import java.util.List;

public class RvWallPaperAdapter extends RecyclerView.Adapter<RvWallPaperAdapter.WallspaperHolder> {
    private Context context;
    private List<Photo> photoList;
    private OnListener onListener;


    public RvWallPaperAdapter(Context context, List<Photo> photoList, OnListener onListener) {
        this.context = context;
        this.photoList = photoList;
        this.onListener = onListener;
    }

    @NonNull
    @Override
    public WallspaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new WallspaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallspaperHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.bindItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListener != null) {
                    onListener.onClickItem(photo);
                } else {
                    onListener.onError();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class WallspaperHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public WallspaperHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bindItem(int position) {
            Glide.with(itemView).load(photoList.get(position).getUrlL()).into(imageView);
        }
    }

    public interface OnListener {
        void onClickItem(Photo photo);

        void onError();
    }
}
