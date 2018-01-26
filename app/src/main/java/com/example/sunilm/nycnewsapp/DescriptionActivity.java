package com.example.sunilm.nycnewsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.sunilm.nycnewsapp.DownloadIages.ImageFile;

public class DescriptionActivity extends Activity {

    Handler handler;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
      //  imageView   =

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                    if (message.what == ImageFile)
                {
                    Bundle data1 = message.getData();
                    data1.keySet();
                    for (String key:
                            data1.keySet()) {
                        byte[] data= message.getData().getByteArray(key);
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        imageView= findViewById(R.id.largeImage);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(bmp);

                    }

                }
                return false;
            }
        });
        Log.d("demo", "i am here");
        //Intent intent = new Intent();
        if(getIntent().getExtras()!=null) {
          //  Log.d("demo", "in method");
            NewsClass newsClass = getIntent().getExtras().getParcelable("parcelablesent");


            TextView tilte = findViewById(R.id.storyTitle);
            TextView description = findViewById(R.id.description);
            TextView postDate = findViewById(R.id.pubdate);
            postDate.setText(newsClass.getPubDate());
            description.setText("Description :" + "\n"+newsClass.getDescription());
            tilte.setText(newsClass.getTitle());
            new DownloadIages(handler,1).execute(newsClass.getContent());



           // Log.d("demo1", newsClass.toString());
        }
       // newsClass.getContent();
    }
}
