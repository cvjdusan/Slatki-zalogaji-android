package com.example.slatkizalogaji.models;

public class Product {
    private int id;
    private String image;
    private String name;
    private String description;
    private double price;
    private String recipe;

    public Product(int id, String image, String name, String description, double price, String recipe) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.recipe = recipe;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getRecipe() {
        return recipe;
    }
}
