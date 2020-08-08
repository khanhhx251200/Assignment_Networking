package com.example.lab4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
    private RvWallPaperAdapter rvWallPaperAdapter;
    private String id = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleries);

        overridePendingTransition(R.anim.slide_in_right, 0);

        iconBack();
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
                intent.putExtra("urlSq", photo.getUrlSq());
                intent.putExtra("urlS", photo.getUrlS());
                intent.putExtra("title", photo.getTitle());
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

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_left);
    }
}
