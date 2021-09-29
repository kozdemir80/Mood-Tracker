package com.example.myapp.model;

public class Moods {
    private  String comment;
    private  int colors;
    private int image;
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



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

    public Moods(String comment, int colors,int image) {
        this.comment = comment;
        this.colors = colors;
        this.image=image;
    }
}
