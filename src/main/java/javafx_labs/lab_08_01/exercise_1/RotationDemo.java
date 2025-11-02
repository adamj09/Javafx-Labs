package javafx_labs.lab_08_01.exercise_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Simple program demonstrating rotation controls.
 */
public class RotationDemo extends Application {
    private final double WIDTH = 450, HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        Ellipse ellipse = new Ellipse(WIDTH / 2, HEIGHT / 2, 30, 20);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.BLACK);

        Pane ellipsePane = new Pane(ellipse);

        Label angleLabel = new Label("Angle (degrees):");
        TextField angleField = new TextField(ellipse.getRotate() + "");
        Button rotateButton = new Button("Rotate");

        HBox buttonBox = new HBox(angleLabel, angleField, rotateButton);
        buttonBox.setSpacing(5);

        BorderPane buttonPane = new BorderPane();
        buttonPane.setBottom(buttonBox);
        buttonPane.setCenter(ellipsePane);
        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        // Rotate the ellipse.
        rotateButton.addEventHandler(ActionEvent.ANY, _ -> {
            try {
                ellipse.setRotate(Double.parseDouble(angleField.getText()));
            } catch (NumberFormatException ex) {
                return; // Abort action if invalid angle is given.
            }
        });

        primaryStage.setScene(new Scene(buttonPane, WIDTH, HEIGHT));
        primaryStage.setTitle("Rotation Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
