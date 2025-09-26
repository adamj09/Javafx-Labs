package javafx_labs.lab_04_01.exercise_1;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class EventHandlerAnonymousClass {
    interface Add {
        void addNumbers();
    }

    public static void setupEventHandler() {
        Add add = new Add() {
            @Override
            public void addNumbers() {
                try {
                    Double num1 = Double.parseDouble(AdditionDemo.num1Field.getText()),
                            num2 = Double.parseDouble(AdditionDemo.num2Field.getText());

                    AdditionDemo.resultField.setText((num1 + num2) + "");
                } catch (Exception ex) {
                    AdditionDemo.resultField.setText("Invalid input!");
                }
            }
        };

        AdditionDemo demo = new AdditionDemo();
        demo.addButton.addEventHandler(ActionEvent.ACTION, _->add.addNumbers());
    }
}
