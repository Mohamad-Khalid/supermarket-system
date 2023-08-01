package com.gomarket.supermarket.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductCRUD {
    public void insert(Product product){
        try {
            String sqlInsert = "insert into Product values(?,?,?,?,?,?)";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlInsert);
            statement.setInt(1,product.getId());
            statement.setInt(2,product.getNumber());
            statement.setString(3,product.getName());
            statement.setDouble(4,product.getDiscount());
            statement.setString(5,product.getType());
            statement.setDouble(6,product.getPrice());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Product insertion failed!");
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void update(Product product){
        try {
            System.out.println("prod id = "+ product.getId());
            String sqlUpdate = "update Product set number=? , name =? , discount=? , type=? , price=? where id="+product.getId();
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlUpdate);
            statement.setInt(1,product.getNumber());
            statement.setString(2,product.getName());
            statement.setDouble(3,product.getDiscount());
            statement.setString(4,product.getType());
            statement.setDouble(5,product.getPrice());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product update failed!");
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }


    public void delete(int id){
        try {
            String sqlDelete = "delete from Product where id=?";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlDelete);
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product delete failed!");
            }

            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public ObservableList getAllProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Product");
            while (results.next()){
                Product tempObj = new Product();
                tempObj.setId(results.getInt(1));
                tempObj.setNumber(results.getInt(2));
                tempObj.setName(results.getString(3));
                tempObj.setDiscount(results.getInt(4));
                tempObj.setType(results.getString(5));
                tempObj.setPrice(results.getDouble(6));
                products.add(tempObj);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(products==null) System.out.println("null 104 productscontrloller");

        return  products;
    }

    public ObservableList getSerchedProducts(String name){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Product WHERE name like '%"+name +"%'");
            while (results.next()){
                Product tempObj = new Product();
                tempObj.setId(results.getInt(1));
                tempObj.setNumber(results.getInt(2));
                tempObj.setName(results.getString(3));
                tempObj.setDiscount(results.getInt(4));
                tempObj.setType(results.getString(5));
                tempObj.setPrice(results.getDouble(6));
                products.add(tempObj);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  products;
    }


}
