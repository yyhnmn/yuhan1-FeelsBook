package com.example.yuhan1_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "fileContent1.sav";
    private ArrayList<String> tweets;
    private static final Integer MAX_CHARS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        updateTextView();
    }

    // save data in file
    // from LonelyTwitter https://github.com/joshua2ua/lonelyTwitter Joshua Charles Campbell
    private void saveInFile(String text, String date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            String content = date + " | " + text;
            fos.write(content.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // get current date in ISO8601 format
    public String getDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateformat.setTimeZone(tz);
        return dateformat.format(new Date());
    }

    // click on HISTORY button to start HistoryList Activity
    public void history(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        intent.putExtra("test", "");
        startActivity(intent);
    }

    public void addJoy(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "joy";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Joy emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    public void addLove(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "love";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Love emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    public void addSurprise(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "surprise";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Surprise emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    public void addAnger(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "anger";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Anger emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    public void addFear(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "fear";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Fear emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    public void addSadness(View view) throws CommentTooLongException{
        EditText commentInput = (EditText) findViewById(R.id.comment);
        String emotion = "sadness";
        String comment = commentInput.getText().toString();
        if (comment.length() <= this.MAX_CHARS) {
            String saveText =  emotion + " | " + comment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(saveText, date);
            updateTextView();
            commentInput.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Sadness emotion added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else {
            throw new CommentTooLongException();
        }
    }

    // update textview for counter of each emotion
    public void updateTextView() {
        ArrayList<String> emotionList = new ArrayList<String>();
        emotionList.add("love");
        emotionList.add("joy");
        emotionList.add("surprise");
        emotionList.add("sadness");
        emotionList.add("anger");
        emotionList.add("fear");
        Counter counter = new Counter();
        tweets = loadFromFile();
        for (String emotion : emotionList) {
            counter.getCountFromText(emotion,tweets);
        }
        TextView counterView = (TextView) findViewById(R.id.counterView);
        counterView.setText("love: " + counter.getLoveCount() + "\n" +
                "joy: " + counter.getJoyCount() + "\n" +
                "surprise: " + counter.getSurpriseCount() + "\n" +
                "anger: " + counter.getAngerCount() + "\n" +
                "sadness: " + counter.getSadnessCount() + "\n" +
                "fear: " + counter.getFearCount());
    }
}

























