package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class Festival_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_item);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");

        new Thread() {
            public void run() {
                GetDbData getData = new GetDbData();
                String result = null;
                System.out.println("ID = " + ID);

                try {
                    result = getData.execute("FESTIVAL", "3", ID).get();
                    StringTokenizer st = new StringTokenizer(result, "#");
                    String name = st.nextToken();
                    String opar = st.nextToken();
                    String start = st.nextToken();
                    String end = st.nextToken();
                    String inform = st.nextToken();
                    String address = st.nextToken();

                    TextView t1 = (TextView)findViewById(R.id.name);
                    TextView t2 = (TextView)findViewById(R.id.opar);
                    TextView t3 = (TextView)findViewById(R.id.startdate);
                    TextView t4 = (TextView)findViewById(R.id.enddate);
                    TextView t5 = (TextView)findViewById(R.id.introduction);
                    TextView t6 = (TextView)findViewById(R.id.address);

                    t1.setText("축제 이름 : " + name);
                    t2.setText("개최 장소 : " + opar);
                    t3.setText("시작 일 : " + start);
                    t4.setText("종료 일 : " + end);
                    t5.setText("축제 소개 : " + inform);
                    t6.setText("도로명 주소 : " + address);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            }
        }.start();
    }

    public void backBtn(View view) {
        finish();
    }
}