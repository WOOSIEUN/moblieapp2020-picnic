package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class city_culture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_culture);
    }

    public void onClickButton1(View view) {
        Intent intent1 = new Intent(city_culture.this , CityList.class);
        startActivity(intent1);
    }
}