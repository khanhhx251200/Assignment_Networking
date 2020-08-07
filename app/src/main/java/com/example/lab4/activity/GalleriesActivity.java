package com.example.lab4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lab4.R;
import com.example.lab4.adapter.RvWallPaperAdapter;
import com.example.lab4.api.RetrofitClient;
import com.example.lab4.model.MyModel;
import com.example.lab4.model.Photo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleriesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Photo> photoList = new ArrayList<>();
    private RvWallPaperAdapter rvWallPaperAdapter;
    private String id = "";
    private String title = "";
    private MyModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleries);

        initUI();
        initGetData();
        setTitle(title);
        callAPI();
        initRecycleView();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callAPI();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void callAPI() {
        RetrofitClient.getInstance().getCategory(id).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if (response.isSuccessful()) {
                    rvWallPaperAdapter.setData(fetchResult(response));

                }
            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {

            }
        });
    }

    private List<Photo> fetchResult(Response<MyModel> response) {
        MyModel myModel = response.body();
        return Arrays.asList(myModel.getPhotos().getPhoto());
    }

    private void initGetData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");


    }

    private void initRecycleView() {
        rvWallPaperAdapter = new RvWallPaperAdapter(this, new RvWallPaperAdapter.OnListener() {
            @Override
            public void onClickItem(Photo photo) {
                Intent intent = new Intent(GalleriesActivity.this, ImageActivity.class);
                intent.putExtra("url", photo.getUrlL());
                startActivity(intent);
            }

            @Override
            public void onError() {

            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(rvWallPaperAdapter);
    }


    private void initUI() {
        recyclerView = findViewById(R.id.rv_galleries);
        swipeRefreshLayout = findViewById(R.id.srl_galleries);
    }
}
