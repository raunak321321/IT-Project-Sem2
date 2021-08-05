import pictures.Picture;

import java.io.*;
import java.util.Scanner;

public class Product {
    private String name;
    private int id;
    private int price;
    private String desc;
    private String picName;
    private int quantity;
    private String retailerName;
    private float rating;

    public Product(int id) {
        Admin.allAboutProducts(id,this);

    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public float getRating() {
        float rating = Admin.letGetRating(this);
        return rating;
    }

    public void getDesc() {
        System.out.println("Description: " + this.desc);
    }

    public void getImage() {
        Picture pic = new Picture(picName);
        pic.show();
    }

    public int getQuantity() {
        return this.quantity;
    }

    void setQuantity(int qty) {
        int id = this.getId();
        String str = this.name + "|" + this.id + "|" + this.picName + "|" + this.desc + "|" + this.price + "|" + qty + "|" + this.retailerName;
        this.quantity = qty;
        Admin.UpdateProductFileLine(id, str);
    }

    public void getReviews() {
        Admin.letGetReviews(this);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setRating(float rate){
        this.rating = rate;
    }
    public void setPicName(String str){
        this.picName = str;
    }
    public void setRetailerName(String name){
        this.retailerName = name;
    }
}