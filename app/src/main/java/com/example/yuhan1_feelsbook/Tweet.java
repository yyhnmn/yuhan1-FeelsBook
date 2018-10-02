package com.example.yuhan1_feelsbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Tweet object which contains information of each feeling
public class Tweet {
    private String date;
    private String emotion;
    private String comment;

    Tweet(String text){
        Pattern p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
        Matcher m = p.matcher(text);
        while(m.find()){
            this.date = m.group();
        }
    }
    Tweet(String date,String emotion, String comment){
        this.date = date;
        this.emotion = emotion;
        this.comment = comment;
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
