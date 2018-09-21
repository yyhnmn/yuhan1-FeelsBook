package com.example.yuhan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryList extends AppCompatActivity {
    private static final String FILENAME = "file2.sav";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.listView1);
    }
    public void onStart(){
        super.onStart();
        String[] feelings = loadFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryList.this,R.layout.itemlayout,feelings);
        listView.setAdapter(adapter);
    }

    private String[] loadFromFile() {
        ArrayList<String> feelings = new ArrayList<String>();
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = in.readLine()) !=null){
                feelings.add(line);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return feelings.toArray(new String[feelings.size()]);
    }
}
