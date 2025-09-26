package javafx_labs.lab_04_01.exercise_1;

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
                    Double num1 = Double.parseDouble(Main.num1Field.getText()),
                            num2 = Double.parseDouble(Main.num2Field.getText());

                    Main.resultField.setText((num1 + num2) + "");
                } catch (Exception ex) {
                    Main.resultField.setText("Invalid input!");
                }
            }
        };

        Main.addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, _->add.addNumbers());
    }
}
