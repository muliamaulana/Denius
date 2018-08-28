package com.muliamaulana.denius.model;

/**
 * Created by muliamaulana on 8/18/2018.
 */

public class TimeAgo {
    private static final int SECOND_MILIS = 1000;
    private static final int MINUTES_MILIS = 60 * SECOND_MILIS;
    private static final int HOUR_MILIS = 60 * MINUTES_MILIS;
    private static final int DAY_MILIS = 24 * HOUR_MILIS;

    public static String getTimeAgo (long time){

        if (time < 1000000000000L){
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0){
            return null;
        }

        final long diff = now - time;
        if (diff < MINUTES_MILIS){
            return "just now";
        } else if (diff < 2 * MINUTES_MILIS){
            return  "a minute ago";
        } else if (diff < 50 * MINUTES_MILIS){
            return  diff/MINUTES_MILIS + " minutes ago";
        } else if (diff < 90 * MINUTES_MILIS){
            return  "an hour ago";
        } else if (diff < 24 * HOUR_MILIS){
            return diff/HOUR_MILIS + " hours ago";
        } else if (diff < 48 * HOUR_MILIS){
            return "Yesterday";
        } else {
            return diff/DAY_MILIS + " days ago";
        }
    }
}
