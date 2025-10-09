package javafx_labs.lab_05_02.exercise_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class demonstrating the use of the RadioButton and CheckBox classes
 *         from Javafx via a simple shape rendering program.
 */
public class GeometricFigures extends Application {
    private Circle circle = new Circle(100);
    private Rectangle square = new Rectangle(100, 100);
    private Ellipse ellipse = new Ellipse(150, 100);

    private RadioButton circleButton = new RadioButton("Circle"), squareButton = new RadioButton("Square"),
            ellipseButton = new RadioButton("Ellipse");

    private CheckBox fillCheckBox = new CheckBox("Fill");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Select Geometric Figures");
        primaryStage.show();

        // Manage visibility of shapes based on the selected RadioButton
        circleButton.addEventHandler(ActionEvent.ACTION, _ -> {
            circle.setVisible(true);
            square.setVisible(false);
            ellipse.setVisible(false);
        });
        squareButton.addEventHandler(ActionEvent.ACTION, _ -> {
            circle.setVisible(false);
            square.setVisible(true);
            ellipse.setVisible(false);
        });
        ellipseButton.addEventHandler(ActionEvent.ACTION, _ -> {
            circle.setVisible(false);
            square.setVisible(false);
            ellipse.setVisible(true);
        });

        // Manage whether the shapes should be rendered as filled using the CheckBox.
        fillCheckBox.addEventHandler(ActionEvent.ACTION, _ -> {
            if (fillCheckBox.isSelected()) {
                circle.setFill(Color.BLACK);
                square.setFill(Color.BLACK);
                ellipse.setFill(Color.BLACK);
            } else {
                circle.setFill(Color.TRANSPARENT);
                square.setFill(Color.TRANSPARENT);
                ellipse.setFill(Color.TRANSPARENT);
            }
        });
    }

    /**
     * Sets up a Scene for rendering
     * 
     * @return the Scene that was set up.
     */
    private Scene setupScene() {
        // Set default colours for all shapes.
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        square.setFill(Color.TRANSPARENT);
        square.setStroke(Color.BLACK);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.BLACK);

        // Set all shapes to be initially invisible.
        circle.setVisible(false);
        square.setVisible(false);
        ellipse.setVisible(false);

        ToggleGroup toggleGroup = new ToggleGroup();
        circleButton.setToggleGroup(toggleGroup);
        squareButton.setToggleGroup(toggleGroup);
        ellipseButton.setToggleGroup(toggleGroup);

        HBox buttonBox = new HBox(circleButton, squareButton, ellipseButton, fillCheckBox);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(10);

        StackPane shapesPane = new StackPane(circle, square, ellipse);
        shapesPane.setPadding(new Insets(10, 10, 10, 10));

        VBox masterBox = new VBox(shapesPane, buttonBox);
        masterBox.setSpacing(20);

        return new Scene(masterBox, 450, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
