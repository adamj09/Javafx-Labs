package javafx_labs.lab_04_01.exercise_1;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class to demonstrate event handling using an anonymous inner class.
 */
public class EventHandlerAnonymousClass {
    interface Add {
        void addNumbers(TextField num1Field, TextField num2Field, TextField resultField);
    }

    public void setupEventHandler(Button button, TextField num1Field, TextField num2Field, TextField resultField) {
        Add add = new Add() {
            @Override
            public void addNumbers(TextField num1Field, TextField num2Field, TextField resultField) {
                try {
                    Double num1 = Double.parseDouble(num1Field.getText()),
                            num2 = Double.parseDouble(num2Field.getText());

                    resultField.setText((num1 + num2) + "");
                } catch (Exception ex) { // Catch any exception thrown when parsing data.
                    resultField.setText("Invalid input!");
                }
            }
        };

        button.addEventHandler(ActionEvent.ACTION, _->add.addNumbers(num1Field, num2Field, resultField));
    }
}
