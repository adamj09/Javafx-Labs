package javafx_labs.lab_08_01.exercise_1;

import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
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
import javafx.stage.Stage;

public class TranslationDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        Rectangle rect = new Rectangle(40, 40, 50, 40);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);

        Pane rectPane = new Pane(rect);

        Label xLabel = new Label("x:"), yLabel = new Label("y:");
        TextField xField = new TextField(rect.getX() + ""), yField = new TextField(rect.getY() + "");
        Button translateButton = new Button("Translate");

        HBox buttonBox = new HBox(xLabel, xField, yLabel, yField, translateButton);
        buttonBox.setSpacing(5);

        BorderPane buttonPane = new BorderPane();
        buttonPane.setBottom(buttonBox);
        buttonPane.setCenter(rectPane);
        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        translateButton.addEventHandler(ActionEvent.ANY, _ -> {
            try {
                rect.setTranslateX(Double.parseDouble(xField.getText()));
                rect.setTranslateY(Double.parseDouble(yField.getText()));
            } catch (NumberFormatException ex) {
                return; // Abort action if invalid positions are given.
            }
        });

        primaryStage.setScene(new Scene(buttonPane, 450, 400));
        primaryStage.setTitle("Translation Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
