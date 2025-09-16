package javafx_labs.lab_03_01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Adam Johnston 2332003
 * A class serving as a basic demonstration of drawing text and shapes using JavaFX.
 * This code was copied from 'Introduction to Java programming' by Liang, and was
 * slightly modified for learning purposes.
 */
public class FontDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a pane to hold the circle.
        Pane pane = new StackPane();

        // Create a circle and set its properties.
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setStroke(Color.STEELBLUE);
        circle.setFill(Color.WHITE);

        // Add the circle to the pane.
        pane.getChildren().add(circle);

        // Create a label and set its properties.
        Label label = new Label("Adam Johnston");
        label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        label.setStyle("-fx-text-fill: #045188;");
        pane.getChildren().add(label);

        // Create a scene and place it in the stage.
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Font Thing"); // Set the stage title.
        primaryStage.setScene(scene); // Place the scene in the stage.
        primaryStage.show(); // Display the stage.
    }

    public static void main(String[] args) {
        launch();
    }
}