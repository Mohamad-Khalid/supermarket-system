package com.gomarket.supermarket.models;
public class Validator {

    public boolean isDouble(String number){
        int dotsCounter = 0;
        for(int i=0; i<number.length(); i++){
            char temp = number.charAt(i);
            if(temp =='.') dotsCounter++;
            else if(!(temp >='0' && temp <='9')) return false;
        }
        if(dotsCounter>1) return false;
        char dot = number.charAt(number.length()-1);
        if(dot == '.' && dotsCounter >1) return false;
        return true;
    }

    public boolean isDigit(char digit){
        if(digit >= '0' && digit <='9' ){
            return true;
        }
        return false;
    }

    public boolean isValidDiscountValue(int value){
        if(value>=0 && value<=100)
            return true;
        return false;
    }

}

