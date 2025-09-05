package javafx_labs.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX start method always structured as follows:
 * 
 * Stage
 * 
 * Scene
 * 
 * Add pane to scene
 * 
 * Add scene to stage
 * 
 * show stage
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button button = new Button();
        Scene scene = new Scene(button, 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
