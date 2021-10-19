package com.example.myapp.model;

public class Moods {
    private  String comment;
    private  int colors;
    private int image;
    private int width;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public Moods(String comment, int colors,int image,int width) {
        this.comment = comment;
        this.colors = colors;
        this.image=image;
        this.width=width;
    }
}
