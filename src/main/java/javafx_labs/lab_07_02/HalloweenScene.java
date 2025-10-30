package javafx_labs.lab_07_02;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 * Program that draws a simple Halloween scene. Used to demo CSS usage in JavaFX.
 */
public class HalloweenScene extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle moon = new Circle(100, 100, 50);

        Circle pumpkin = new Circle(600, 400, 100);
        Arc mouth = new Arc(pumpkin.getCenterX(), pumpkin.getCenterY() + 25, 40, 40, 180, 180);

        // Pumpkin's eyes
        Path eyes = new Path(
            new MoveTo(pumpkin.getCenterX() - 25, pumpkin.getCenterY() - 15),
            new LineTo(pumpkin.getCenterX() - 45, pumpkin.getCenterY() - 50),
            new LineTo(pumpkin.getCenterX() - 65, pumpkin.getCenterY() - 15),
            new LineTo(pumpkin.getCenterX() - 25, pumpkin.getCenterY() - 15),

            new MoveTo(pumpkin.getCenterX() + 25, pumpkin.getCenterY() - 15),
            new LineTo(pumpkin.getCenterX() + 45, pumpkin.getCenterY() - 50),
            new LineTo(pumpkin.getCenterX() + 65, pumpkin.getCenterY() - 15),
            new LineTo(pumpkin.getCenterX() + 25, pumpkin.getCenterY() - 15)
        );

        Line treeTrunk = new Line();
        treeTrunk.setStartX(pumpkin.getCenterX() + pumpkin.getRadius() * 2);
        treeTrunk.setStartY(pumpkin.getCenterY() + pumpkin.getRadius());
        treeTrunk.setEndX(treeTrunk.getStartX());
        treeTrunk.setEndY(treeTrunk.getStartY() - 400);

        Path treeBranches = new Path(
            new MoveTo(treeTrunk.getStartX(), treeTrunk.getEndY() + 100),
            new LineTo(treeTrunk.getStartX() - 100, treeTrunk.getEndY() + 50),
            new MoveTo(treeTrunk.getStartX(), treeTrunk.getEndY() + 100),
            new LineTo(treeTrunk.getStartX() + 100, treeTrunk.getEndY() + 50)
        );

        Group objects = new Group(moon, pumpkin, mouth, eyes, treeTrunk, treeBranches);

        Scene scene = new Scene(objects, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());
        scene.setFill(Color.rgb(1, 27, 59, 1)); // Make sky dark blue

        // Add CSS style classes to objects
        moon.getStyleClass().add("moon");
        pumpkin.getStyleClass().add("pumpkin");
        mouth.getStyleClass().add("mouth");
        eyes.getStyleClass().add("eyes");
        treeTrunk.getStyleClass().add("tree-trunk");
        treeBranches.getStyleClass().add("tree-branches");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Halloween Scene");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
