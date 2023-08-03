package com.gomarket.supermarket.models;

public class Bill {
    public double calculateTotal(Product product , int wantedQuantity){
        int availableQuantity = product.getNumber();
        double total = 0;
        if(availableQuantity >= wantedQuantity){
            total = (product.getPrice() * wantedQuantity) - (product.getDiscount()/100.0 * product.getPrice());
        }

        return total;
    }

    public String generateBill(Product product , int wantedQuantity, double total){
        return "---------------Bill ---------------"+
                "\nProduct Name : " + product.getName() +
                "\nProduct Type : " + product.getType() +
                "\nProduct Price : "+ product.getPrice()+
                "\n" + "Quantity : "+ wantedQuantity+
                "\nTotal : "        + total;
    }
}
