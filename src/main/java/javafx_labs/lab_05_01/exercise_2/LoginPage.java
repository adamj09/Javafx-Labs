package javafx_labs.lab_05_01.exercise_2;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage extends Application {
    private Label loginInfoLabel = new Label(),
            verificationLabel = new Label();

    private TextField userField = new TextField(),
            passField = new TextField();

    private Button loginButton = new Button("Login");

    private String username = "Username",
            password = "Password";

    @Override
    public void start(Stage primaryStage) {
        loginButton.addEventHandler(ActionEvent.ACTION, _ -> {
            loginInfoLabel
                    .setText("Username entered: " + userField.getText() + "\nPassword entered: " + passField.getText());
                    
            if (userField.getText().equals(username) && passField.getText().equals(password)) {
                verificationLabel.setText("User is verified!");
            } else if (!passField.getText().equals(password) && userField.getText().equals(username)) {
                verificationLabel.setText("Incorrect password!");
            } else if (!userField.getText().equals(username)) {
                verificationLabel.setText("User does not exist!");
            }
        });

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    private Scene setupScene() {
        Label prompt = new Label("Enter your username and password:"),
                userLabel = new Label("Username:"),
                passLabel = new Label("Password:");

        GridPane loginPane = new GridPane(10, 10);
        loginPane.add(prompt, 1, 0);
        loginPane.add(userLabel, 0, 1);
        loginPane.add(userField, 1, 1);
        loginPane.add(passLabel, 0, 2);
        loginPane.add(passField, 1, 2);
        loginPane.add(loginButton, 1, 3);
        loginPane.add(loginInfoLabel, 1, 4);
        loginPane.add(verificationLabel, 1, 5);
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setAlignment(Pos.CENTER);

        return new Scene(loginPane, 300, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
