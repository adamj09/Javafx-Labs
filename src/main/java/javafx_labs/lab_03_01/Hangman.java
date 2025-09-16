package javafx_labs.lab_03_01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 *         A class that draws a simple, stick-figure, hangman in a window using
 *         JavaFX.
 */
public class Hangman extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Base of the gallows
        Arc gallowsBase = new Arc(50.0f, 250.0f, 25.0f, 15.0f, 0.0f, 180.0f);
        gallowsBase.setType(ArcType.OPEN);
        gallowsBase.setFill(Color.TRANSPARENT);
        gallowsBase.setStroke(Color.BLACK);

        // Gallows
        Line gallowsPole = new Line(gallowsBase.getCenterX(), gallowsBase.getCenterY() - gallowsBase.getRadiusY(),
                gallowsBase.getCenterX(), 50.0f),
                gallowsBeam = new Line(gallowsPole.getEndX(), gallowsPole.getEndY(), 150.0f, gallowsPole.getEndY()),
                rope = new Line(gallowsBeam.getEndX(), gallowsBeam.getEndY(), gallowsBeam.getEndX(),
                        gallowsBeam.getEndY() + 50.0f);

        // Head of the hangman
        Circle head = new Circle(rope.getEndX(), rope.getEndY() + 20.0f, 20.0f);
        head.setFill(Color.TRANSPARENT);
        head.setStroke(Color.BLACK);

        // Body of the hangman
        Line body = new Line(head.getCenterX(), head.getCenterY() + head.getRadius(), head.getCenterX(), 200.0f),
            leftArm = new Line(body.getStartX(), body.getStartY() + 10.0f, body.getEndX() - 35.0f,
            body.getStartY() - 10.0f),
            rightArm = new Line(body.getStartX(), body.getStartY() + 10.0f, body.getEndX() + 35.0f,
            body.getStartY() - 10.0f),
            leftLeg = new Line(body.getEndX(), body.getEndY(), body.getEndX() - 35.0f, body.getEndY() + 35.0f),
            rightLeg = new Line(body.getEndX(), body.getEndY(), body.getEndX() + 35.0f, body.getEndY() + 35.0f);

        // Add all parts of the hangman and gallows to the pane.
        Pane pane = new Pane();
        pane.getChildren().addAll(gallowsBase, gallowsPole, gallowsBeam, rope, head, body, leftArm, rightArm, leftLeg,
                rightLeg);

        // Draw the final result.
        Scene scene = new Scene(pane, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hangman");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}