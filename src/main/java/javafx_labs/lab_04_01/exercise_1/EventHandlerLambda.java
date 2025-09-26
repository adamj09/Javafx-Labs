package javafx_labs.lab_04_01.exercise_1;

import javafx.scene.input.MouseEvent;

public class EventHandlerLambda {
    private static void addNumbers() {
        try {
            Double num1 = Double.parseDouble(Main.num1Field.getText()),
                    num2 = Double.parseDouble(Main.num2Field.getText());

            Main.resultField.setText((num1 + num2) + "");
        } catch (Exception ex) {
            Main.resultField.setText("Invalid input!");
        }
    }

    public static void setupEventHandler() {
        Main.addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, _->addNumbers());
    }
}
