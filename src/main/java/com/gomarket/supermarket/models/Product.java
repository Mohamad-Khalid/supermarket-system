package com.gomarket.supermarket.models;

public class Product {

    private int id;
    private int number;
    private String name;
    private double price;
    private int discount;

    private String type;


    public Product(){
        this.number = 0;
        this.name ="";
        this.price =0;
        this.discount = 0;
        this.type ="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "\n id : " + id +
                "\n number : " + number +
                "\n name : " + name  +
                "\n price : " + price +
                "\n discount : " + discount +
                "\n type= : " + type ;

    }
}
