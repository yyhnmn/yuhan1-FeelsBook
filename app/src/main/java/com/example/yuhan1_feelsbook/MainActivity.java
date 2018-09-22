package com.example.yuhan1_feelsbook;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "fileContent.sav";
    private static final String CFILENAME = "fileCounter.sav";
    private EditText commentInput;
    private Spinner spinner;
    private String saveText;
    private String comment;
    private int loveCount;
    private int joyCount;
    private int surpriseCount;
    private int angerCount;
    private int sadnessCount;
    private int fearCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromCFile();
        setTextView();
        adder();
    }
    private void setTextView(){
        TextView counterView = (TextView)findViewById(R.id.counterView);

        counterView.setText("love: "+ loveCount+ "\n" +
                            "joy: "+ joyCount+ "\n" +
                            "surprise: "+ surpriseCount+ "\n" +
                            "anger: "+ angerCount+ "\n" +
                            "sadness: "+ sadnessCount+ "\n" +
                            "fear: "+ fearCount);
    }
    private void saveInFile(String text, Date date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_APPEND);
            String content = date.toString()+" | " + text;
            fos.write(content.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int countNumber(String src, String findText){
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(src);
        while(m.find()){
            count++;
        }
        return count;
    }
    public void adder(){
        Button addEmotion = (Button) findViewById(R.id.adder);
        addEmotion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                commentInput = (EditText) findViewById(R.id.comment);
                spinner = (Spinner) findViewById(R.id.spinner1);
                String selectedEmotion = spinner.getSelectedItem().toString();
                comment = commentInput.getText().toString();
                saveText = selectedEmotion+" | " + comment;
                setResult(RESULT_OK);
                saveInFile(saveText, new Date(System.currentTimeMillis()));
                saveInCFile(selectedEmotion);
                loadFromCFile();
                setTextView();
            }
        });
    }

    public void history(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        intent.putExtra("test", "");
        startActivity(intent);
    }


    private void loadFromCFile() {
        String emotions;
        try {
            FileInputStream fis = openFileInput(CFILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            emotions = in.readLine();
            loveCount = countNumber(emotions, "love");
            joyCount = countNumber(emotions, "joy");
            surpriseCount = countNumber(emotions, "surprise");
            angerCount = countNumber(emotions, "anger");
            sadnessCount = countNumber(emotions, "sadness");
            fearCount = countNumber(emotions, "fear");
        } catch (FileNotFoundException e) {
            loveCount =0;
            joyCount =0;
            surpriseCount=0;
            angerCount=0;
            sadnessCount=0;
            fearCount=0;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveInCFile(String text){
        try {
            FileOutputStream fos = openFileOutput(CFILENAME,Context.MODE_APPEND);
            fos.write(text.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}