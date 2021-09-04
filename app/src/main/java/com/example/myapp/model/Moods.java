package com.example.myapp.model;

public class Moods {
    public static String comment;
    public static int colors;

    public static String getComment() {
        return comment;
    }

    public static void setComment(String comment) {
        Moods.comment = comment;
    }

    public static int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public Moods() {
        comment = comment;
        this.colors = colors;
    }
}
