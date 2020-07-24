package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {
    private Intent intent;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = findViewById(R.id.image_detail);
        intent = getIntent();
        String url = intent.getStringExtra("url");
        Glide.with(this).load(url).into(imageView);
    }
}
