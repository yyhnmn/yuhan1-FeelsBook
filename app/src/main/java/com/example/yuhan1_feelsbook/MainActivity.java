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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "fileContent1.sav";
    private EditText commentInput;
    private Spinner spinner;
    private String saveText;
    private String comment;
    private ArrayList<String> feelings;
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
        adder();
    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        updateTextView();
    }

    private void saveInFile(String text,String date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_APPEND);
            String content = date +" | " + text;
            fos.write(content.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<String> loadFromFile() {
        feelings = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = in.readLine()) != null) {
                feelings.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feelings;
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
                String date = getDate();
                saveInFile(saveText,date);
                updateTextView();
            }
        });
    }

    public String getDate(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateformat.setTimeZone(tz);
        String nowAsISO = dateformat.format(new Date());
        return nowAsISO;
    }

    public void history(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        intent.putExtra("test", "");
        startActivity(intent);
    }


    public void updateCounter(String text){
        feelings = loadFromFile();
        int count = 0;
        for(String line:feelings){
            Pattern p = Pattern.compile(text);
            Matcher m = p.matcher(line);
            while(m.find()){
                count++;
                break;
            }
        }
        if(text.equals("love")){
            loveCount=count;
        }
        else if(text.equals("joy")){
            joyCount = count;
        }
        else if(text.equals("sadness")){
            sadnessCount = count;
        }
        else if(text.equals("anger")){
            angerCount = count;
        }
        else if(text.equals("surprise")){
            surpriseCount = count;
        }
        else if(text.equals("fear")){
            fearCount = count;
        }
    }

    public void updateTextView(){
        ArrayList<String> emotionList = new ArrayList<String>();
        emotionList.add("love");
        emotionList.add("joy");
        emotionList.add("surprise");
        emotionList.add("sadness");
        emotionList.add("anger");
        emotionList.add("fear");
        for(String emotion:emotionList){
            updateCounter(emotion);
        }
        setTextView();
    }

    public void setTextView(){
        TextView counterView = (TextView)findViewById(R.id.counterView);
        counterView.setText("love: "+ loveCount+ "\n" +
                "joy: "+ joyCount+ "\n" +
                "surprise: "+ surpriseCount+ "\n" +
                "anger: "+ angerCount+ "\n" +
                "sadness: "+ sadnessCount+ "\n" +
                "fear: "+ fearCount);
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

//    private void loadFromCFile() {
//        String emotions;
//        try {
//            FileInputStream fis = openFileInput(CFILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            emotions = in.readLine();
//            loveCount = countNumber(emotions, "love");
//            joyCount = countNumber(emotions, "joy");
//            surpriseCount = countNumber(emotions, "surprise");
//            angerCount = countNumber(emotions, "anger");
//            sadnessCount = countNumber(emotions, "sadness");
//            fearCount = countNumber(emotions, "fear");
//        } catch (FileNotFoundException e) {
//            loveCount =0;
//            joyCount =0;
//            surpriseCount=0;
//            angerCount=0;
//            sadnessCount=0;
//            fearCount=0;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}