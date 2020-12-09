package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class Heritage_item extends AppCompatActivity {
    Bitmap bitmap;
    ImageView image;
    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage_item);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        TextView t = findViewById(R.id.introduction);
        t.setMovementMethod(new ScrollingMovementMethod());

        Thread mthread = new Thread() {
            public void run() {
                GetDbData getData = new GetDbData();
                String result = null;
                System.out.println("ID = " + ID);

                try {
                    result = getData.execute("HERITAGE", "3", ID).get();
                    StringTokenizer st = new StringTokenizer(result, "#");
                    String name = st.nextToken();
                    String type = st.nextToken();
                    String address = st.nextToken();
                    String period = st.nextToken();
                    String intro = st.nextToken();
                    url = st.nextToken();

                    image = (ImageView)findViewById(R.id.image);
                    TextView t1 = (TextView)findViewById(R.id.name);
                    TextView t2 = (TextView)findViewById(R.id.type);
                    TextView t3 = (TextView)findViewById(R.id.address);
                    TextView t4 = (TextView)findViewById(R.id.period);
                    TextView t5 = (TextView)findViewById(R.id.introduction);

                    t1.setText("문화재 명 : " + name);
                    t2.setText("종목: " + type);
                    t3.setText("주소 : " + address);
                    t4.setText("시대 : " + period);
                    t5.setText("문화재 소개 : " + intro);

                    URL URL = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection)URL.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            }
        };
        mthread.start();

        try{
            mthread.join();
            image.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void backBtn(View view) {
        finish();
    }
}