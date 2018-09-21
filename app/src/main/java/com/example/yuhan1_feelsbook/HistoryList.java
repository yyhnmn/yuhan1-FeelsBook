package com.example.yuhan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryList extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        listView = (ListView)findViewById(R.id.listView1);
        Intent intent = getIntent();
    }
    public void onStart(){
        super.onStart();
        String[] tweets = loadFromFile();
        
    }

    private String[] loadFromFile() {
    }
}
