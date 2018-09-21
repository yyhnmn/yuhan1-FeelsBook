package com.example.yuhan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void history(View view){
        EditText dateInput = (EditText)findViewById(R.id.date);
        String date = dateInput.getText().toString();
        Intent intent = new Intent(this, HistoryList.class);
//        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        intent.putExtra("test","");
        startActivity(intent);
    }

}