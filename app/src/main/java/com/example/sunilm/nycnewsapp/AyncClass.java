package com.example.sunilm.nycnewsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sunilm on 1/23/2018.
 */

public class AyncClass extends AsyncTask<String , String, ArrayList<NewsClass>>{

    Handler handler;
    public AyncClass(Handler handler)
    {
        this.handler = handler;
    }
 /*   public static ArrayList generate() {

        ArrayList<NewsClass> arr = new ArrayList<>();
      arr.add(new NewsClass("1","me"));
        arr.add(new NewsClass("2","me2"));
        arr.add(new NewsClass("3","me3"));
        arr.add(new NewsClass("4","me4"));
        return arr;

    }*/
public final static int FINAL_Result = 0x00;
    @Override
    protected ArrayList doInBackground(String... strings) {
        try{

            URL url = new URL("http://rss.nytimes.com/services/xml/rss/nyt/World.xml");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


            httpURLConnection.setRequestMethod("GET");
            // Log.d("demo","i did not got here");
            httpURLConnection.connect();
            int statusCode = httpURLConnection.getResponseCode();
            if(statusCode== HttpURLConnection.HTTP_OK)
            {
                // Log.d("demo","i got here");
                 InputStream in =  httpURLConnection.getInputStream();
           /*     StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while(line!=null)
                {
                    sb.append(line);
                    line= br.readLine();
                }*/
                return XmlParserClass.Util.parseNews(in);
            }
            return null;
        }
        catch (Exception e){
            // Log.d("demo","error is"+e.getMessage());

        }
        finally {

        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<NewsClass> arrayList) {
        Log.d("demo","post execute"+arrayList.toString());
        Message msg = new Message();
        msg.what =FINAL_Result;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("result",arrayList);
        msg.setData(bundle);
        handler.sendMessage(msg);
        super.onPostExecute(arrayList);
    }
}
