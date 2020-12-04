package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CityList extends AppCompatActivity {
    ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_city_list) ;

        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.add("가");
        arraylist.add("나");
        arraylist.add("다");
        arraylist.add("라");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist) ;

        ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;

                Intent intent = new Intent(CityList.this, city_list_item.class);
                intent.putExtra("image","");
                intent.putExtra("type", "");
                intent.putExtra("name","");
                intent.putExtra("where", "");
                intent.putExtra("date", "");
                intent.putExtra("describe", "");

                startActivity(intent);
            }
        }) ;
    }
}