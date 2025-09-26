package javafx_labs.lab_04_01.exercise_1;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Adam Johnston 2332003
 * 
 * Class to demonstrate event handling using a lambda function.
 */
public class EventHandlerLambda {
    private static void addNumbers() {
        try {
            Double num1 = Double.parseDouble(AdditionDemo.num1Field.getText()),
                    num2 = Double.parseDouble(AdditionDemo.num2Field.getText());

            AdditionDemo.resultField.setText((num1 + num2) + "");
        } catch (Exception ex) {
            AdditionDemo.resultField.setText("Invalid input!");
        }
    }

    public static void setupEventHandler() {
        //AdditionDemo demo = new AdditionDemo();
        AdditionDemo.addButton.addEventHandler(ActionEvent.ACTION, _->addNumbers());
    }
}
