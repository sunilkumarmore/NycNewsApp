package com.example.sunilm.nycnewsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import static com.example.sunilm.nycnewsapp.AyncClass.FINAL_Result;
import static com.example.sunilm.nycnewsapp.DownloadIages.ImageFile;

public class MainActivity extends Activity {
Handler handler;

    ArrayList<NewsClass> nnnn = new ArrayList<>();
    ArrayList<ImageView> imageVieArraylist = new ArrayList<>();
    ArrayList<View> listItemArrayList = new ArrayList<>();
int p = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


handler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message message) {
        if(message.what ==FINAL_Result )
        {
            if (message.getData().containsKey("result")) {

                nnnn= message.getData().getParcelableArrayList("result");
                Log.d("demo",""+nnnn);
                LinearLayout container = new LinearLayout(MainActivity.this);
                container.setOrientation(LinearLayout.VERTICAL);
                for (int i = 0; i< nnnn.size()-1;i++){
                    NewsClass user = nnnn.get(i);
                    p=i;
                    ListItem item = new ListItem(MainActivity.this);
                    final View itemView = (View)item;
                    item.first.setText("First: "+user.title);

                    item.iv.setVisibility(View.VISIBLE);
                    imageVieArraylist.add(item.iv);

                    new DownloadIages(handler,i).execute(user.getContent());
                    //item.iv.setImageBitmap();
                    listItemArrayList.add(itemView);
                   // item.last.setText("Last: "+user.description);
                    container.addView(itemView);
                    itemView.setOnClickListener(new View.OnClickListener()
                    {

                        @Override
                        public void onClick(View view) {
                           p= listItemArrayList.lastIndexOf(itemView);
                            Intent nn = new Intent(MainActivity.this,DescriptionActivity.class);
                            nn.putExtra("parcelablesent",nnnn.get(p));
                            startActivity(nn);

                        }
                    });
                }
                ScrollView sv_main = (ScrollView) findViewById(R.id.svmain);
                sv_main.addView(container);
            }

        }
        else if (message.what == ImageFile)
        {
            Bundle data1 = message.getData();
           data1.keySet();
            for (String key:
                 data1.keySet()) {
                byte[] data= message.getData().getByteArray(key);
                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
              ImageView iv=  imageVieArraylist.get(Integer.parseInt(key));
                iv.setImageBitmap(bmp);

            }

            }


        return false;
    }
});

               new AyncClass(handler).execute("not imortant");





       // ArrayList<NewsClass> listOfUsers = new ArrayList<>();
      // new AyncClass(handler).execute("sad");



    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
/*        for (int i =0; i<listItemArrayList.size()-1;i++)
        {
            View bb= listItemArrayList.get(i);
            p=i;
            bb.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view) {

                    Intent nn = new Intent(MainActivity.this,DescriptionActivity.class);
                    nn.putExtra("",nnnn.get(p));
                    startActivity(nn);

                }
            });

        }*/
    }


}
