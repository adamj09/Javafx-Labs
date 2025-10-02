package javafx_labs.lab_04_02.exercise_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class to demonstrate the setOnMousePressed() and setOnMouseReleased()
 *         functions.
 */
public class MouseColorDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, Color.RED);

        FlowPane pane = new FlowPane(circle);
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane, 400, 400);

        // Green when mouse pressed when focused on scene, red otherwise.
        scene.setOnMousePressed(_ -> {
            circle.setFill(Color.rgb(113, 219, 77));
        });
        scene.setOnMouseReleased(_ -> {
            circle.setFill(Color.RED);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mouse Color Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
