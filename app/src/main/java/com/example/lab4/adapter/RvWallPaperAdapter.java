package com.example.lab4.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4.R;
import com.example.lab4.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class RvWallPaperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Photo> photoList;
    private OnListener onListener;
    public int VIEW_TYPE_ITEM = 0;
    public int VIEW_TYPE_LOADING = 1;


    public RvWallPaperAdapter(Context context, OnListener onListener) {
        this.context = context;
        this.photoList = new ArrayList<>();
        this.onListener = onListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
            return new WallspaperHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        if (holder instanceof WallspaperHolder) {
            populateItemRows((WallspaperHolder) holder, position);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder instanceof LoadingViewHolder) {
                    return;
                }
                if (onListener != null) {
                    onListener.onClickItem(photo);
                } else {
                    onListener.onError();
                }
            }
        });
    }

    private void populateItemRows(WallspaperHolder holder, int position) {

        Glide.with(holder.itemView).load(photoList.get(position).getUrlM()).into(((WallspaperHolder) holder).imageView);
        holder.tvViews.setText("Views: " + photoList.get(position).getViews());
    }


    @Override
    public int getItemViewType(int position) {
        return photoList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void addLoadMore() {
        photoList.add(null);
        notifyItemInserted(photoList.size() - 1);
    }

    public void removeLoadmore() {
        if (photoList.get(photoList.size() - 1) == null) {
            int removePosition = photoList.size() - 1;
            photoList.remove(removePosition);
            notifyItemRemoved(removePosition);
        }
    }

    private class WallspaperHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvViews;

        public WallspaperHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvViews = itemView.findViewById(R.id.tv_views);
        }

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnListener {
        void onClickItem(Photo photo);

        void onError();
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void addData(List<Photo> list) {
        removeLoadmore();
        int last_size = photoList.size();
        photoList.addAll(list);
        notifyItemRangeInserted(last_size, list.size());
    }

    public void setData(List<Photo> photoList) {
        this.photoList.clear();
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }
}
