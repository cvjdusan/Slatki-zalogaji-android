package com.example.slatkizalogaji.models;

public class Promotion {
    private String title;
    private String description;
    private int imageResourceId;

    public Promotion(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
