package com.example.lab4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4.R;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class FanpageActivity extends AppCompatActivity {
    private ImageView btnRefresh;
    private ImageView btnNext;
    private ImageView btnPrevious;
    private ImageView btnBack;
    private WebView mWebView;
    private TextView inputUrl;
    private ContentLoadingProgressBar mProgressBar;
    private SwipeRefreshLayout refreshLayout;

    boolean isLoading = true;
    String url1 = "https://www.facebook.com/HuyNguyenAndroid/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanpage);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);


        initUI();
        mProgressBar.setVisibility(View.GONE);
        initListenner();
        initWebView();

        loadHTTP();

    }

    private void loadHTTP() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(url1);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
        thread.start();
    }

    private void initUI() {
        setTitle("Fanpage");
        btnRefresh = findViewById(R.id.button_refresh_url);
        btnNext = findViewById(R.id.button_next);
        btnBack = findViewById(R.id.button_back);
        btnPrevious = findViewById(R.id.button_previous);
        mWebView = findViewById(R.id.webview);
        inputUrl = findViewById(R.id.text_url);
        mProgressBar = findViewById(R.id.progress_loading);
        refreshLayout = findViewById(R.id.swipe_refresh);

        inputUrl.setText(url1);
    }

    private void initListenner() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                mWebView.loadUrl(inputUrl.getText().toString());
                refreshLayout.setRefreshing(false);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mWebView.loadUrl(inputUrl.getText().toString());


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mWebView.goForward();

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mWebView.goBack();

            }
        });
    }

    private void initWebView() {
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                Log.d("TAG", "onProgressChanged: " + newProgress);

                if (newProgress >= 0 && newProgress < 100) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }

            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                inputUrl.setText(url);

                if (mWebView.canGoBack()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    btnPrevious.setImageDrawable(getResources().getDrawable(R.drawable.ic_enable_back));
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    btnPrevious.setImageDrawable(getResources().getDrawable(R.drawable.ic_disable_back));
                }
                if (mWebView.canGoForward()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    btnNext.setImageDrawable(getResources().getDrawable(R.drawable.ic_enable_forward));
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    btnNext.setImageDrawable(getResources().getDrawable(R.drawable.ic_disable_forward));
                }
            }
        });

        mWebView.loadUrl(inputUrl.getText().toString());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_right);
    }
}