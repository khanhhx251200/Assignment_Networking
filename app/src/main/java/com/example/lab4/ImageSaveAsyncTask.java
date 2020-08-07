package com.example.lab4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.lab4.activity.ImageActivity;

public class ImageSaveAsyncTask extends AsyncTask<String, Void, String> {
    private ProgressDialog pDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
//            pDialog.setMessage("Downloading file. Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setProgress(0);
//            pDialog.setMax(100);
//            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            pDialog.setCancelable(false);
//            pDialog.show();

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
