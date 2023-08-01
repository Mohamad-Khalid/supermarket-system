package com.gomarket.supermarket.models;
import com.gomarket.supermarket.controllers.Utility;
import com.gomarket.supermarket.models.Validator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BadInputBlocker {
    Validator validator = new Validator();
    public void preventCharInput(TextField textField){
        String number = textField.getText();
        if(number.length() > 0){
            if(!validator.isDigit(number.charAt(number.length()-1))){
                textField.deletePreviousChar();
            }
        }
        else {
            Utility.clearForm(textField);
        }
    }

    public void preventNonDoubleInput(TextField textField){
        String number =textField.getText();
        if(number.length()>0){
            if(!validator.isDouble(textField.getText())){
                textField.deletePreviousChar();
            }
        }
    }

    public void preventInvalidDiscountValue(TextField textField){
        if(textField.getText().length()>0){
            int discountValue = Integer.parseInt(textField.getText());
            if(!validator.isValidDiscountValue(discountValue)){
                Utility.clearForm(textField);
            }
        }
    }

    public boolean notEmptyData(ComboBox comboBoxe, TextField...textFields){
       boolean okFeilds = true;
       if (comboBoxe.getValue() == null || comboBoxe.getValue().toString().isEmpty() ) okFeilds = false;
       for(int i=0 ;i<textFields.length;i++){
           if(textFields[i].getText() == null || textFields[i].getText().isEmpty() ) okFeilds = false;
       }
       return okFeilds;
    }

    public boolean notEmptyData(DatePicker datePicker, ComboBox comboBoxe, TextField...textFields){
        boolean okFeilds = notEmptyData(comboBoxe,textFields);
        if(datePicker.getValue() == null ||datePicker.getValue().toString().isEmpty() ) okFeilds = false;
        return okFeilds;
    }


}
