package com.example.yuhan1_feelsbook;

import java.util.Comparator;

public class SortDate implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Tweet tweet1 = new Tweet(o1.toString());
        Tweet tweet2 = new Tweet(o2.toString());
        String s1 = (tweet1).getDate();
        String s2 = (tweet2).getDate();
        if(s1 == null || s2 == null){
            return 0;
        }
        else {
            return -s1.compareTo(s2);
        }
    }
}
