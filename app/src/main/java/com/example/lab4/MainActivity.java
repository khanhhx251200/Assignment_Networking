package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab4.api.ApiService;
import com.example.lab4.api.ApiUtils;
import com.example.lab4.model.MyModel;
import com.example.lab4.model.Photo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAlbumPhotos;
    private ApiService mApiService;
    private RvWallPaperAdapter rvWallPaperAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    List<Photo> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAlbumPhotos = findViewById(R.id.rv_album_photos);
        swipeRefreshLayout = findViewById(R.id.srl);

        callAPI();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                callAPI();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void callAPI() {

        mApiService = ApiUtils.getApiService();

        mApiService.getData(String.valueOf(page)).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if (response.isSuccessful()) {
                    MyModel myModel = response.body();
                    list = Arrays.asList(myModel.getPhotos().getPhoto());
                    initDataToRecycle(list);
                }
            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {

            }
        });
    }

    private void initDataToRecycle(List<Photo> list) {
        rvWallPaperAdapter = new RvWallPaperAdapter(MainActivity.this, list, new RvWallPaperAdapter.OnListener() {
            @Override
            public void onClickItem(Photo photo) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra("url", photo.getUrlL());
                startActivity(intent);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Load Server Fail", Toast.LENGTH_SHORT).show();
            }
        });
        rvAlbumPhotos.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvAlbumPhotos.setAdapter(rvWallPaperAdapter);
        rvWallPaperAdapter.notifyDataSetChanged();
    }


}
