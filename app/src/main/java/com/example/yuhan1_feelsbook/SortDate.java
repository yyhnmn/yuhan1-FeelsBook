package com.example.yuhan1_feelsbook;

import java.util.Comparator;

// override compare method to help sort data using Collections.sort()
// from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date May 8 '11 at 12:29 Domchi
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
