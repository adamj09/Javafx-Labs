package javafx_labs.lab_04_01.exercise_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label num1Label = new Label("Number 1: "),
                num2Label = new Label("Number 2: "),
                resultLabel = new Label("Result: ");

        TextField num1Field = new TextField(),
                num2Field = new TextField(),
                resultField = new TextField();

        resultField.setEditable(false);

        Button addButton = new Button("Add");

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

        Scene scene = new Scene(gridPane, 300, 300);

        primaryStage.setTitle("Event Handler Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
