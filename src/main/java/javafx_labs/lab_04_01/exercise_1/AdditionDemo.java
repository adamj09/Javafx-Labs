package javafx_labs.lab_04_01.exercise_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class used to demonstrate different methods of event handling via a
 *         simply addition program.
 */
public class AdditionDemo extends Application {
    //TODO: make this not static
    static Button addButton = new Button("Add");

    static TextField num1Field = new TextField(),
            num2Field = new TextField(),
            resultField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        resultField.setEditable(false);

        // Handle button click event
        EventHandlerLambda.setupEventHandler();
        // EventHandlerAnonymousClass.setupEventHandler();
        // EventHandlerInnerClass.setupEventHandler();

        primaryStage.setTitle("Event Handler Demo");
        primaryStage.setScene(setupScene());
        primaryStage.show();
    }

    /**
     * Sets up the scene with all labels, fields, and the button.
     * @return The scene that was set up.
     */
    private Scene setupScene() {
        Label num1Label = new Label("Number 1: "),
                num2Label = new Label("Number 2: "),
                resultLabel = new Label("Result: ");

        GridPane gridPane = new GridPane();
        gridPane.add(num1Label, 0, 0);
        gridPane.add(num1Field, 1, 0);

        gridPane.add(num2Label, 0, 1);
        gridPane.add(num2Field, 1, 1);

        gridPane.add(resultLabel, 0, 2);
        gridPane.add(resultField, 1, 2);

        gridPane.add(addButton, 1, 3);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        return new Scene(gridPane, 300, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
