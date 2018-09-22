package com.example.yuhan1_feelsbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HistoryList extends AppCompatActivity {
    private static final String FILENAME = "fileContent.sav";
    private static final String CFILENAME = "fileCounter.sav";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> feelings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.listView1);

    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        listViewFunc();
    }

    public void listViewFunc(){
        feelings = loadFromFile();
        adapter = new ArrayAdapter<String>(HistoryList.this,R.layout.itemlayout,feelings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEditbox(feelings.get(position),position);
            }
        });
    }
    public void showEditbox(String oldItem, final int index){
        final Dialog dialog = new Dialog(HistoryList.this);
        dialog.setTitle("editbox");
        dialog.setContentView(R.layout.editbox);
        final EditText edittext1 = (EditText)dialog.findViewById(R.id.editText1);
        edittext1.setText(oldItem);
        Button editbutton = (Button)dialog.findViewById(R.id.editbutton);
        Button deletebutton = (Button)dialog.findViewById(R.id.deletebutton);
        editbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                feelings.set(index , edittext1.getText().toString());
                saveInFile(feelings);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        deletebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                feelings.remove(index);
                saveInFile(feelings);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private ArrayList<String> loadFromFile() {
        feelings = new ArrayList<String>();
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
        return feelings;
    }
    private void saveInFile(ArrayList<String> list) {
        deleteFile(FILENAME);
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            for(String text:list){
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
