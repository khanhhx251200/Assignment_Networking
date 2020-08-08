package com.example.lab4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
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
    private ProgressDialog pDialog;

    private String urlL;
    private String urlS;
    private String urlSq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);

        imageView = findViewById(R.id.image_detail);
        pDialog = new ProgressDialog(this);

        iconBack();
        getDataFromIntent();

        Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        animFadeOut.reset();
        imageView.clearAnimation();
        imageView.startAnimation(animFadeOut);

//        TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
//                .addTransition(new ChangeBounds())
//                .addTransition(new ChangeImageTransform()));
//
//        ViewGroup.LayoutParams params = imageView.getLayoutParams();
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        imageView.setLayoutParams(params);
//
//        imageView.setScaleType(
//                ImageView.ScaleType.FIT_CENTER);


        Glide.with(this).load(urlL).into(imageView);

    }

    private void getDataFromIntent() {
        intent = getIntent();
        urlL = intent.getStringExtra("url");
        urlSq = intent.getStringExtra("urlSq");
        urlS = intent.getStringExtra("urlS");

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
            case android.R.id.home:
                finish();
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

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(drawable);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_left);
    }
}
