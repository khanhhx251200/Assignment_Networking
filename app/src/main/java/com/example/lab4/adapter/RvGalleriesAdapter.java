package com.example.lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.R;
import com.example.lab4.model.Category;
import com.example.lab4.model.Photo;

import java.util.List;

public class RvGalleriesAdapter extends RecyclerView.Adapter<RvGalleriesAdapter.GalleriesHolder> {
    private Context context;
    private List<Category> categoryList;
    private OnListener mListner;

    public RvGalleriesAdapter(Context context, List<Category> categoryList, OnListener mListner) {
        this.context = context;
        this.categoryList = categoryList;
        this.mListner = mListner;
    }

    @NonNull
    @Override
    public GalleriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_galleries, parent, false);
        return new GalleriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleriesHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bindItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListner != null) {
                    mListner.onClickItem(category);
                } else {
                    mListner.onError();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class GalleriesHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public GalleriesHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void bindItem(int position) {
            tvTitle.setText(categoryList.get(position).title);
        }
    }

    public interface OnListener {
        void onClickItem(Category category);

        void onError();
    }
}
