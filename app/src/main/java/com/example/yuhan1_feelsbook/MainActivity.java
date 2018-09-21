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
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file2.sav";
    private EditText commentInput;
    private EditText dateInput;
    private Spinner spinner;
    private String saveText;
    private String comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addEmotion = (Button) findViewById(R.id.adder);
        addEmotion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                commentInput = (EditText) findViewById(R.id.comment);
                spinner = (Spinner) findViewById(R.id.spinner1);
                String selectedEmotion = spinner.getSelectedItem().toString();
                comment = commentInput.getText().toString();
                saveText = selectedEmotion+comment;
                setResult(RESULT_OK);

                saveInFile(saveText, new Date(System.currentTimeMillis()));

            }
        });
    }

    private void saveInFile(String text, Date date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
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

    public void history(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        intent.putExtra("test", "");
        startActivity(intent);
    }

}