package com.gomarket.supermarket.models;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Validator {

    public boolean isDouble(String number){
        int dotsCounter = 0;
        for(int i=0; i<number.length(); i++){
            char temp = number.charAt(i);
            if(temp =='.') dotsCounter++;
            else if(!(temp >='0' && temp <='9')) return false;
        }
        return dotsCounter <= 1;
    }

    public boolean isDigit(char digit){
        return digit >= '0' && digit <= '9';
    }

    public boolean isValidDiscountValue(int value){
        return value >= 0 && value <= 100;
    }


    public boolean isEmptyData(ComboBox comboBoxe, TextField...textFields){
        boolean emptyFeilds = comboBoxe.getValue() == null || comboBoxe.getValue().toString().isEmpty();
        for (TextField textField : textFields) {
            if (textField.getText() == null || textField.getText().isEmpty()) emptyFeilds = true;
        }
        return emptyFeilds;
    }

    public boolean isEmptyData(DatePicker datePicker, ComboBox comboBoxe, TextField...textFields){
        boolean emptyFeilds = isEmptyData(comboBoxe,textFields);
        if(datePicker.getValue() == null ||datePicker.getValue().toString().isEmpty() ) emptyFeilds = true;
        return emptyFeilds;
    }

}

