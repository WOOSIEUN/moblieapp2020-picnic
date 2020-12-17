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

public class Tour_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_item);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");

        new Thread() {
            public void run() {
                GetDbData getData = new GetDbData();
                String result = null;
                System.out.println("ID = " + ID);

                try {
                    result = getData.execute("TOUR", "3", ID).get();
                    StringTokenizer st = new StringTokenizer(result, "#");
                    String name = st.nextToken();
                    String inform = st.nextToken();
                    String manage = st.nextToken();
                    String address = st.nextToken();

                    if(name.equals("null"))
                        name = "제공되지 않는 정보입니다.";
                    if(inform.equals("null"))
                        inform = "제공되지 않는 정보입니다.";
                    if(address.equals("null"))
                        address = "제공되지 않는 정보입니다.";
                    if(manage.equals("null"))
                        manage = "제공되지 않는 정보입니다.";

                    TextView t1 = (TextView)findViewById(R.id.name);
                    TextView t2 = (TextView)findViewById(R.id.introduction);
                    TextView t3 = (TextView)findViewById(R.id.manage);
                    TextView t4 = (TextView)findViewById(R.id.address);

                    t1.setText("관광지 명 : " + name);
                    t2.setText("관광지 소개 : " + inform);
                    t3.setText("관리 기관명 : " + manage);
                    t4.setText("도로명 주소 : " + address);

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