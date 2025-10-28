package javafx_labs.lab_07_01.exercise_1;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Adam Johnston 2332003
 * 
 *         A class that plays a simple pendulum swinging animation.
 */
public class PendulumAnimation extends Application {
    @Override
    public void start(Stage primaryStage) {
        int sceneWidth = 400, sceneHeight = 400;

        // Arc along which the circle will move.
        Arc arc = new Arc(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2 - 25, sceneWidth / 2 - 25, 190, 160);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);

        // Circle to be moved.
        Circle circle = new Circle(arc.getCenterX(), arc.getCenterY() + arc.getRadiusY(), 9);
        circle.setFill(Color.BLUE);

        // Swinging animation.
        PathTransition pathTransition = new PathTransition(Duration.seconds(3), arc, circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH); // Smooth acceleration while animation plays.
        pathTransition.play();

        Group objects = new Group(arc, circle);

        Scene scene = new Scene(objects, sceneWidth, sceneHeight);

        // Pause and play animation.
        scene.setOnMouseClicked(_ -> {
            if (pathTransition.getStatus() == Status.RUNNING) {
                pathTransition.pause();
            } else if (pathTransition.getStatus() == Status.PAUSED) {
                pathTransition.play();
            }
        });

        primaryStage.setTitle("Pendulum Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
