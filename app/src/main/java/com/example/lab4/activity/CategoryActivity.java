package com.example.lab4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab4.R;
import com.example.lab4.adapter.RvGalleriesAdapter;
import com.example.lab4.api.RetrofitClient;
import com.example.lab4.model.Category;
import com.example.lab4.model.MyModel;
import com.example.lab4.model.Photo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RvGalleriesAdapter rvGalleriesAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Photo> photoList;
    private List<Category> categoryList = new ArrayList<>();

    private MyModel model = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");

        initUI();
        getGalleries();
        initDataToRecycle(categoryList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGalleries();
                rvGalleriesAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void getGalleries() {
        categoryList.add(new Category("72157712150290108", "BW: A Your Best Shot 2019 Gallery"));
        categoryList.add(new Category("72157712232083312", "All About Portraits: a Your Best Shot 2019 gallery"));
        categoryList.add(new Category("72157712233171141", "Stunning Landscapes: a Your Best Shot 2019 gallery"));
        categoryList.add(new Category("72157675059930493", "Street View: a Flickr Your Best Shot 2016 gallery"));
        categoryList.add(new Category("72157663337771571", "Your Best Shot 2015: Macros and Close-Ups"));
        categoryList.add(new Category("72157662859243450", "Your Best Shot 2015: A Wide Expanse"));
        categoryList.add(new Category("72157654379251348", "Dad's Day"));
        categoryList.add(new Category("72157648989290536", "31 days of Halloween makeup"));
        categoryList.add(new Category("72157648514173258", "Yahoo On the Road"));
        categoryList.add(new Category("72157646896444515", "Awesome skyscapes"));
        categoryList.add(new Category("72157690671472841", "Happy 10th Anniversary, Flickr Commons!"));
        categoryList.add(new Category("72157691024360322", "Flickr Friday - There Is No Place Like Home"));
        categoryList.add(new Category("72157697209149355", "Flickr Friday - Sign of the Times"));
        categoryList.add(new Category("72157703406532791", "Your Best Shot 2018: Black and White"));
        categoryList.add(new Category("72157713540927126", "Flickr Friday - Play Ball!"));
        categoryList.add(new Category("72157713821402061", "Flickr Friday - Music"));
        categoryList.add(new Category("72157715102362628", "Summer 2020"));
        categoryList.add(new Category("72157713970734808", "Happy Earth Day 2020"));
        categoryList.add(new Category("72157714011589476", "Flickr Friday - I Stay Home"));
        categoryList.add(new Category("72157713471498036", "35 Female Photographers to Follow on Flickr"));
        categoryList.add(new Category("72157715273262927", "Wear a Mask"));
        categoryList.add(new Category("72157715212376691", "Flickr Friday - Polished"));
        categoryList.add(new Category("72157714772062607", "The History of Juneteenth told Through Photographs"));
        categoryList.add(new Category("72157714306692258", "Flickr Friday - Family time"));
        categoryList.add(new Category("72157668862914729", "Caught in the Middle"));
        Collections.shuffle(categoryList);

    }


    private void initDataToRecycle(List<Category> categoryList) {
        rvGalleriesAdapter = new RvGalleriesAdapter(this, categoryList, new RvGalleriesAdapter.OnListener() {
            @Override
            public void onClickItem(Category category) {
                Intent intent = new Intent(CategoryActivity.this, GalleriesActivity.class);
                intent.putExtra("id", category.id);
                intent.putExtra("title", category.title);
                startActivity(intent);
            }

            @Override
            public void onError() {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
        recyclerView.setAdapter(rvGalleriesAdapter);
    }

    private void initUI() {
        recyclerView = findViewById(R.id.rv_category);
        swipeRefreshLayout = findViewById(R.id.sfl_category);
    }
}
