package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChoiceActivity extends AppCompatActivity {
    private String[] splitedStr;
    static final String[] LIST_CITY = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남"
            , "전북", "전남", "경북", "경남", "제주"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void Festival(View view) {
        ArrayList<String> arraylist = new ArrayList<String>();

        ListView listview = (ListView) findViewById(R.id.listview) ;
        for(int i=0; i<LIST_CITY.length; i++){
            arraylist.add(LIST_CITY[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;

                final String[] Location = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주"};
                final int pos = position;
                new Thread() {
                    public void run() {
                        GetDbData getData = new GetDbData();
                        String result = null;
                        try {
                            result = getData.execute("FESTIVAL", "2", Location[pos]).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                }.start();

            }
        }) ;
    }

    public void Tour(View view) {
        ArrayList<String> arraylist = new ArrayList<String>();

        ListView listview = (ListView) findViewById(R.id.listview) ;
        for(int i=0; i<LIST_CITY.length; i++){
            arraylist.add(LIST_CITY[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                final String[] Location = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주"};
                final int pos = position;
                new Thread() {
                    public void run() {
                        GetDbData getData = new GetDbData();
                        String result = null;
                        try {
                            result = getData.execute("TOUR", "2", Location[pos]).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                }.start();

            }
        }) ;
    }

    public void Heritage(View view) {
        ArrayList<String> arraylist = new ArrayList<String>();

        ListView listview = (ListView) findViewById(R.id.listview) ;
        for(int i=0; i<LIST_CITY.length; i++){
            arraylist.add(LIST_CITY[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                final String[] Location = {"11", "21", "22", "23", "24", "25", "26", "31", "32", "33", "34", "35", "36", "37", "38", "50"};
                final int pos = position;
                new Thread() {
                    public void run() {
                        GetDbData getData = new GetDbData();
                        String result = null;
                        try {
                            result = getData.execute("HERITAGE", "2", Location[pos]).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                }.start();
            }
        }) ;
    }
}