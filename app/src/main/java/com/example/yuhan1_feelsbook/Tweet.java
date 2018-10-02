package com.example.yuhan1_feelsbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Tweet object which contains information of each feeling
public class Tweet {
    private String date;
    private String emotion;
    private String comment;

    Tweet(String line){
        Pattern p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
        Matcher m = p.matcher(line);
        while(m.find()){
            this.date = m.group();
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getEmotion() {
        return emotion;
    }
}
