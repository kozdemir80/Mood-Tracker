package com.example.myapp.model;

public class Moods {
    private  String comment;
    private  int colors;



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public Moods(String comment, int colors) {
        this.comment = comment;
        this.colors = colors;
    }
}
