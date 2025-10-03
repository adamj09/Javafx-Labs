package javafx_labs.lab_05_01.exercise_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficLight extends Application {
    private Circle greenLight, redLight, yellowLight;
    private RadioButton greenButton, yellowButton, redButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Traffic Light");
        primaryStage.show();

        greenButton.addEventHandler(ActionEvent.ACTION, _->{
            greenLight.setFill(Color.rgb(132, 234, 37, 1));
            redLight.setFill(Color.WHITE);
            yellowLight.setFill(Color.WHITE);
        });

        yellowButton.addEventHandler(ActionEvent.ACTION, _->{
            yellowLight.setFill(Color.rgb(237, 206, 26, 1));
            redLight.setFill(Color.WHITE);
            greenLight.setFill(Color.WHITE);
        });

        redButton.addEventHandler(ActionEvent.ACTION, _->{
            redLight.setFill(Color.rgb(234, 37, 37, 1));
            greenLight.setFill(Color.WHITE);
            yellowLight.setFill(Color.WHITE);
        });
    }

    private Scene setupScene() {
        Rectangle rectangle = new Rectangle((1.0 / 3) * 200, 200, Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        double rectCenterX = rectangle.getX() + rectangle.getWidth() / 2;
        double lightRadius = (rectangle.getWidth() / 2) - 5;

        yellowLight = new Circle(rectCenterX, (rectangle.getY() + rectangle.getHeight() / 2), lightRadius, Color.WHITE);
        yellowLight.setStroke(Color.BLACK);

        redLight = new Circle(rectCenterX, yellowLight.getCenterY() - lightRadius * 2 - 5, lightRadius, Color.WHITE);
        redLight.setStroke(Color.BLACK);

        greenLight = new Circle(rectCenterX, yellowLight.getCenterY() + lightRadius * 2 + 5, lightRadius, Color.WHITE);
        greenLight.setStroke(Color.BLACK);

        Group shapes = new Group(rectangle, greenLight, yellowLight, redLight);

        ToggleGroup toggleGroup = new ToggleGroup();

        greenButton = new RadioButton("Green");
        yellowButton = new RadioButton("Yellow");
        redButton = new RadioButton("Red");

        greenButton.setToggleGroup(toggleGroup);
        yellowButton.setToggleGroup(toggleGroup);
        redButton.setToggleGroup(toggleGroup);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(greenButton, yellowButton, redButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        BorderPane pane = new BorderPane();
        pane.setCenter(shapes);
        pane.setBottom(hBox);

        return new Scene(pane, 300, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
