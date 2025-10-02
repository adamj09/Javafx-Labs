package javafx_labs.lab_04_02.exercise_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class to demonstrate collision detection using the mouse cursor and a
 *         rectangle.
 */
public class MouseCollisionDetection extends Application {
    @Override
    public void start(Stage primaryStage) {
        Rectangle rectangle = new Rectangle(80, 50, 100, 30);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        // Create and position label.
        Label label = new Label("");
        FlowPane pane = new FlowPane(label);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(new Group(pane, rectangle), 400, 200);

        // Change label based on whether the mouse is inside or outside the circle.
        rectangle.setOnMouseEntered(_ -> {
            label.setText("Mouse point is inside the rectangle.");
        });
        rectangle.setOnMouseExited(_ -> {
            label.setText("Mouse point is outside the rectangle.");
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Collision Detection Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
