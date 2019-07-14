package com.example.celebrityapp;

public class Celebrity {

    String name;
    String image;

    public Celebrity(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Celebrity() {
        this.name = "";
        this.image = "";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
