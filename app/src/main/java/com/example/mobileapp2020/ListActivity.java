package com.example.mobileapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobileapp2020.R;
import com.example.mobileapp2020.ResultActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> aryList = new ArrayList<String>();
        aryList.add("강원도");
        aryList.add("광주광역시");
        aryList.add("경기도");
        aryList.add("경상북도");
        aryList.add("경상남도");
        aryList.add("대구광역시");
        aryList.add("대전광역시");
        aryList.add("부산광역시");
        aryList.add("서울특별시");
        aryList.add("울산광역시");
        aryList.add("인천광역시");
        aryList.add("전라북도");
        aryList.add("전라남도");
        aryList.add("충청북도");
        aryList.add("충청남도");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                aryList
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String category = (String)spinner.getSelectedItem().toString();
                        String locate = (String)adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(view.getContext(), ResultActivity.class);
                        intent.putExtra("CATEGORY", category);
                        intent.putExtra("LOCATE", locate);
                        startActivity(intent);
                    }
                }
        );
    }
}