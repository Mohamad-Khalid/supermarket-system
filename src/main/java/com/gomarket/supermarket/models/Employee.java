package com.gomarket.supermarket.models;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String phoneNumber;
    private double salary;
    private String city;
    private LocalDate joinDate;

    public Employee(){
        this.name = "";
        this.phoneNumber = "";
        this.salary = 0;
        this.city="";
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getJoinDate() {
        return  joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return  "\n id : " + id +
                "\n name :  " + name +
                "\n phoneNumber : " + phoneNumber +
                "\n salary : " + salary +
                "\n city : " + city  +
                "\n joinDate : " + joinDate ;
    }
}
