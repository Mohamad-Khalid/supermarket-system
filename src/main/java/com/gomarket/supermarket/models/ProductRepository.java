package com.gomarket.supermarket.models;

import com.gomarket.supermarket.controllers.DataSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {

    DataSetter dataSetter = new DataSetter();
    public void insert(Product product){
        try {
            String sqlInsert = "insert into Product values(?,?,?,?,?,?)";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlInsert);
            dataSetter.setProductInsertStatement(statement,product);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Product insertion failed!");
            }
            DBConnection.closeDBConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Product product){
        try {
            String sqlUpdate = "update Product set  number=? , name =? , discount=? , type=? , price=? where id="+product.getId();
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlUpdate);
            dataSetter.setProductUpdateStatement(statement , product);
            statement.executeUpdate();
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public void delete(int id){
        try {
            String sqlDelete = "delete from Product where id=?";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlDelete);
            statement.setInt(1,id);
            statement.executeUpdate();
            DBConnection.closeDBConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public ObservableList getAllProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Product");
            while (results.next()){
                Product product = dataSetter.setProductData(results);
                products.add(product);
            }
            DBConnection.closeDBConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return  products;
    }

    public ObservableList getSearchedProducts(String name){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Product WHERE name like '%"+name +"%'");
            while (results.next()){
                Product product = dataSetter.setProductData(results);
                products.add(product);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  products;
    }


}
