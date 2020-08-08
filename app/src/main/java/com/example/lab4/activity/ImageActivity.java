package com.example.lab4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lab4.ImageSaveAsyncTask;
import com.example.lab4.R;

public class ImageActivity extends AppCompatActivity {
    private Intent intent;
    private ImageView imageView;
    private ProgressBar progressBar;
    private ProgressDialog pDialog;
    private TextView tvProgress;
    private String urlL;
    private String urlS;
    private String urlSq;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.image_detail);

        pDialog = new ProgressDialog(this);


        getDataFromIntent();

        Glide.with(this).load(urlL).into(imageView);
    }

    private void getDataFromIntent() {
        intent = getIntent();
        urlL = intent.getStringExtra("url");
        urlSq = intent.getStringExtra("urlSq");
        urlS = intent.getStringExtra("urlS");
        title = intent.getStringExtra("title");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getDataFromIntent();
        switch (item.getItemId()) {
            case R.id.menu_down_Sq:
                downloadImage(urlSq);
                break;
            case R.id.menu_down_S:
                downloadImage(urlS);
                break;
            case R.id.menu_downL:
                downloadImage(urlL);
                break;


        }

        return super.onOptionsItemSelected(item);

    }

    private void downloadImage(String url) {

        ImageSaveAsyncTask imageSaveAsyncTask = new ImageSaveAsyncTask(this, new ImageSaveAsyncTask.AsyncInterface() {
            @Override
            public void OnProgressChange(int value) {
                showProgress(value);
            }

            @Override
            public void OnpostExute(String s) {
                pDialog.dismiss();
                Toast.makeText(ImageActivity.this, "Đã tải xong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnPreExcute() {

            }
        });

        imageSaveAsyncTask.execute(url);
    }

    private void showProgress(int value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pDialog.setCancelable(true);
                pDialog.setProgress(value);
                pDialog.setTitle("Loading…");
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setMax(100);
                pDialog.show();


            }
        });

    }
}
