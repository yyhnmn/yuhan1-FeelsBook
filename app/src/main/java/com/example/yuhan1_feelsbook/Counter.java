package com.example.yuhan1_feelsbook;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.provider.Telephony.Mms.Part.FILENAME;

// Counter class to count emotions from file
public class Counter {
    private int loveCount;
    private int joyCount;
    private int surpriseCount;
    private int angerCount;
    private int sadnessCount;
    private int fearCount;

    public void getCountFromText(String emotion, ArrayList<String> tweets){
        int count = 0;
        for (String line : tweets) {
            Pattern p = Pattern.compile("\\| " + emotion + " \\|");
            Matcher m = p.matcher(line);
            while (m.find()) {
                count++;
                break;
            }
        }
        if (emotion.equals("love")) {
            loveCount = count;
        } else if (emotion.equals("joy")) {
            joyCount = count;
        } else if (emotion.equals("sadness")) {
            sadnessCount = count;
        } else if (emotion.equals("anger")) {
            angerCount = count;
        } else if (emotion.equals("surprise")) {
            surpriseCount = count;
        } else if (emotion.equals("fear")) {
            fearCount = count;
        }
    }

    public int getSurpriseCount() {
        return surpriseCount;
    }

    public int getSadnessCount() {
        return sadnessCount;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public int getJoyCount() {
        return joyCount;
    }

    public int getFearCount() {
        return fearCount;
    }

    public int getAngerCount() {
        return angerCount;
    }

}

