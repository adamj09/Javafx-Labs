package javafx_labs.lab_04_01.exercise_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class to demonstrate event handling through the creation of a basic
 *         calculator.
 */
public class Calculator extends Application {
    private Button addButton = new Button("Add"), subButton = new Button("Subtract"),
            multButton = new Button("Multiply"), divButton = new Button("Divide"), powButton = new Button("Power");

    private TextField num1Field = new TextField(), num2Field = new TextField(), resultField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        // Setup event handlers for each calculator operation.
        CalculatorHandler handler = new CalculatorHandler();
        addButton.addEventHandler(ActionEvent.ACTION, _ -> handler.add(num1Field, num2Field, resultField));
        subButton.addEventHandler(ActionEvent.ACTION, _ -> handler.subtract(num1Field, num2Field, resultField));
        multButton.addEventHandler(ActionEvent.ACTION, _ -> handler.multiply(num1Field, num2Field, resultField));
        divButton.addEventHandler(ActionEvent.ACTION, _ -> handler.divide(num1Field, num2Field, resultField));
        powButton.addEventHandler(ActionEvent.ACTION, _ -> handler.power(num1Field, num2Field, resultField));

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Calculator Demo");
        primaryStage.show();
    }

    /**
     * Sets up the entire scene for display.
     * 
     * @return The scene that was set up.
     */
    private Scene setupScene() {
        Label num1Label = new Label("Number 1: "), num2Label = new Label("Number 2: "),
                resultLabel = new Label("Result: ");

        num1Field.setEditable(true);
        num2Field.setEditable(true);
        resultField.setEditable(false);

        // Display 10 digits per field.
        num1Field.setPrefColumnCount(10);
        num2Field.setPrefColumnCount(10);
        resultField.setPrefColumnCount(10);

        FlowPane labelPane = new FlowPane();
        labelPane.setHgap(10);
        labelPane.setAlignment(Pos.CENTER);
        labelPane.getChildren().addAll(num1Label, num1Field, num2Label, num2Field, resultLabel, resultField);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, subButton, multButton, divButton, powButton);

        BorderPane master = new BorderPane();
        master.setCenter(labelPane);
        master.setBottom(buttonBox);

        return new Scene(master, 600, 200);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
