package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {
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


            }
        }) ;
    }
}