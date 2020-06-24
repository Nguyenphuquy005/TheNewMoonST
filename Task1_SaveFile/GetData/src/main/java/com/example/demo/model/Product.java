package com.example.demo.model;

import java.util.List;

public class Product {
    String name;
    String price ;
    List<String> images ;

    public Product() {
    }

    public Product(String name, String price, List<String> img) {
        this.name = name;
        this.price = price;
        this.images = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> img) {
        this.images = img;
    }

}
