package com.example.sunilm.nycnewsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sunilm on 1/25/2018.
 */

public class DownloadIages extends AsyncTask<String,Integer,Bitmap> {
    public final static int ImageFile = 0X02;
    Handler handler;
    int imagePlaceHolder;
    public DownloadIages(Handler handler, int imagePlaceHolder)
    {
this.handler = handler;
        this.imagePlaceHolder = imagePlaceHolder;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            if(con!=null)
            {
                InputStream in = con.getInputStream();
                image = BitmapFactory.decodeStream(con.getInputStream());

                if (image != null) {
                    // sendMsg("File Retrieved");
                    // Log.d("demo", "File Retrieved");
                } else {
                    // sendMsg("File Error");
                    // Log.d("demo", "File error");
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        Message msg = new Message();
        msg.what = ImageFile;
        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            //Log.d("demo", "completed post exeute" + byteArray);
            bundle.putByteArray(String.valueOf(imagePlaceHolder), byteArray);

            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        super.onPostExecute(bitmap);
    }
}
