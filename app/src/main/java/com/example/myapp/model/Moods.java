package com.example.myapp.model;

public class Moods {
    private  String comment;
    private  int colors;
    private int image;

    public Moods(int width) {
        this.width = width;
    }

    private  int width;

    public int getWidth(int moodColorsArray) {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


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

    public int getColors(int moodColorsArray) {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public Moods(String comment, int colors, int image,int width) {
        this.comment = comment;
        this.colors = colors;
        this.image=image;
        this.width=width;

    }
}
