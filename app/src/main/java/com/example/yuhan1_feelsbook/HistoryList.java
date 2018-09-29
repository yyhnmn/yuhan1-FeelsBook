package com.example.yuhan1_feelsbook;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class HistoryList extends AppCompatActivity {
    private static final String FILENAME = "fileContent1.sav";
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        listViewFunc();
    }

    // show the history list of feelings
    public void listViewFunc() {
        ListView listView = (ListView) findViewById(R.id.listView1);
        tweets = loadFromFile();
        SortDate sortDate = new SortDate();
        Collections.sort(tweets, sortDate);
        adapter = new ArrayAdapter<String>(HistoryList.this, R.layout.itemlayout, tweets);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEditbox(tweets.get(position), position);
            }
        });
    }

    // Use dialog interface for editting and deleting feelings
    public void showEditbox(String oldItem, final int index) {
        final Dialog dialog = new Dialog(HistoryList.this);
        dialog.setTitle("editbox");
        dialog.setContentView(R.layout.editbox);
        final EditText edittext1 = (EditText) dialog.findViewById(R.id.editText1);
        edittext1.setText(oldItem);
        Button editbutton = (Button) dialog.findViewById(R.id.editbutton);
        Button deletebutton = (Button) dialog.findViewById(R.id.deletebutton);
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edittext1.getText().toString().matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2} \\| (love|sadness|joy|fear|anger|surprise) \\| .*?$"))) {
                    tweets.set(index, edittext1.getText().toString());
                    saveInFile(tweets);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "input format is wrong\nsample inpu:\n2018-09-23T10:06:47 | anger | comment", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                listViewFunc();
            }
        });
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tweets.remove(index);
                saveInFile(tweets);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    // load data from file
    // from LonelyTwitter https://github.com/joshua2ua/lonelyTwitter Joshua Charles Campbell
    private ArrayList<String> loadFromFile() {
        tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = in.readLine()) != null) {
                tweets.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweets;

    }

    // save data in file
    // from LonelyTwitter https://github.com/joshua2ua/lonelyTwitter Joshua Charles Campbell
    private void saveInFile(ArrayList<String> list) {
        deleteFile(FILENAME);
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            for (String text : list) {
                fos.write(text.getBytes());
                fos.write("\r\n".getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}