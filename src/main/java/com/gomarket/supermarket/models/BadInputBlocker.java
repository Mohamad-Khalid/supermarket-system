package com.gomarket.supermarket.models;
import com.gomarket.supermarket.controllers.Utility;
import javafx.scene.control.TextField;

public class BadInputBlocker {
    Validator validator = new Validator();
    // The function deletes the last digit user input if it is not digit(0-9)
    public void preventCharInput(TextField textField){
        String number = textField.getText();
        if(number.length() > 0){
            if(!validator.isDigit(number.charAt(number.length()-1))){
                textField.deletePreviousChar();
            }
        }
    }

    // The function deletes the last digit user input if the number not double
    public void preventNonDoubleInput(TextField textField){
        String number = textField.getText();
        if(number.length()>0){
            if(!validator.isDouble(textField.getText())){
                textField.deletePreviousChar();
            }
        }
    }

    // The function deletes the value of the discount if it is not invalid
    public void preventInvalidDiscountValue(TextField textField){
        if(textField.getText().length()>0){
            int discountValue = Integer.parseInt(textField.getText());
            if(!validator.isValidDiscountValue(discountValue)){
                Utility.clearForm(textField);
            }
        }
    }

}
