package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class city_list_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list_item);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("image");
        String type = intent.getExtras().getString("type");
        String name = intent.getExtras().getString("name");
        String where = intent.getExtras().getString("where");
        String date = intent.getExtras().getString("date");
        String describe = intent.getExtras().getString("describe");


    }
}