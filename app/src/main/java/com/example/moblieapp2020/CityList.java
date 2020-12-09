package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;

public class CityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_city_list) ;

        Intent intent = getIntent();
        final ArrayList<String> ID = intent.getStringArrayListExtra("ID");;
        final ArrayList<String> Name = intent.getStringArrayListExtra("Name");
        final String mode = intent.getStringExtra("mode");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Name) ;

        ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Intent intent1 = null;

                if(mode.equals("Festival")){
                    intent1 = new Intent(CityList.this, Festival_item.class);
                    intent1.putExtra("ID", ID.get(position));
                    startActivity(intent1);
                }
                else if(mode.equals("Tour")){
                    intent1 = new Intent(CityList.this, Tour_item.class);
                    intent1.putExtra("ID", ID.get(position));
                    startActivity(intent1);
                }
                else if(mode.equals("Heritage")){
                    intent1 = new Intent(CityList.this, Heritage_item.class);
                    intent1.putExtra("ID", ID.get(position));
                    startActivity(intent1);
                }
            }
        }) ;
    }
}