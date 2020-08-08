package com.example.lab4;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;


import java.io.File;

public class ImageSaveAsyncTask extends AsyncTask<String, Integer, String> {
    private ProgressDialog pDialog;
    private AsyncInterface mAsyncTask;
    private Context context;


    public ImageSaveAsyncTask(Context context, AsyncInterface mAsyncTask) {
        this.mAsyncTask = mAsyncTask;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mAsyncTask != null) {
            mAsyncTask.OnPreExcute();
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        try {


            for (int i = 0; i <= 100; i++) {
                Thread.sleep(25);
                publishProgress(i);

            }
            String filename = ".jpg";
            int name = 0;
            String downloadUrlOfImage = strings[0];
            String DIR_NAME = "images";
            File direct =
                    new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .getAbsolutePath() + "/" + DIR_NAME + "/");


            if (!direct.exists()) {
                direct.mkdir();
            }

            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(downloadUrlOfImage);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            name += 1;
            String filenames = String.valueOf(name) + filename;
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                            File.separator + DIR_NAME + File.separator + filenames);

            dm.enqueue(request);

        } catch (Exception e) {

        }
        return "ĐÃ TẢI XONG";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (mAsyncTask != null) {
            mAsyncTask.OnpostExute(result);
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mAsyncTask != null) {
            mAsyncTask.OnProgressChange(values[0]);
        }
    }

    public interface AsyncInterface {
        void OnProgressChange(int value);

        void OnpostExute(String s);

        void OnPreExcute();
    }
}
