package com.example.lab4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAlbumPhotos;
    private RvWallPaperAdapter rvWallPaperAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private SearchView searchView;
    List<Photo> list = new ArrayList<>();
    MyModel myModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAlbumPhotos = findViewById(R.id.rv_album_photos);
        swipeRefreshLayout = findViewById(R.id.srl);
        initActionBar();
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

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();

    }

    private void callAPI() {
        RetrofitClient.getInstance().getData(String.valueOf(page)).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                myModel = response.body();
                Log.e("SSS", "onResponse: " + myModel);
                list = Arrays.asList(myModel.getPhotos().getPhoto());
                initDataToRecycle(list);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    return false;    //some operation
                }

            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search...");
            View searchPlateView = searchView.findViewById(androidx.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:

                break;
            case R.id.menu_search:

                break;
            case R.id.menu_category:
                startActivity(new Intent(this, CategoryActivity.class));
                break;
            case R.id.menu_fanpage:

                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
