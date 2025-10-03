package javafx_labs.lab_05_01.exercise_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Demo login page that loads required login info from a txt file.
 */
public class LoginPageTextFile extends Application {
    private Label loginInfoLabel = new Label(),
            verificationLabel = new Label();

    private TextField userField = new TextField(),
            passField = new TextField();

    private Button loginButton = new Button("Login");

    private HashMap<String, String> loginInfo = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        loginButton.addEventHandler(ActionEvent.ACTION, _ -> loginHandler());

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Login Page");
        primaryStage.show();

        loadLoginInfo();
        if (loginInfo.size() == 0) {
            // Login info could not be found: abort.
            System.err.println("Login info could not be found!");
            primaryStage.close();
        }
    }

    /**
     * Compares login info from the txt file with login info that was entered.
     */
    private void loginHandler() {
        loginInfoLabel
                .setText("Username entered: " + userField.getText() + "\nPassword entered: " + passField.getText());

        // If the username does exist, check whether its associated password is
        // correctly given.
        if (loginInfo.containsKey(userField.getText())) {
            if (loginInfo.get(userField.getText()).equals(passField.getText())) {
                verificationLabel.setText("User is verified!");
            } else {
                verificationLabel.setText("Incorrect password!");
            }
            return;
        }
        // If the username doesn't exist, state so.
        verificationLabel.setText("User does not exist!");
    }

    /**
     * Loads usernames and associated passwords into a hashmap from txt file.
     */
    private void loadLoginInfo() {
        File file = new File("src/main/resources/users.txt");
        try {
            Scanner scanner = new Scanner(file);
            // Clear arrays so that calling this method multiple times doesn't duplicate
            // info.
            loginInfo.clear();

            while (scanner.hasNextLine()) {
                loginInfo.put(scanner.next(), scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (NoSuchElementException ex) {
            // Clear arrays since the info wasn't properly formatted.
            loginInfo.clear();
        }
    }

    /**
     * Sets up a Scene.
     * 
     * @return the Scene that was set up.
     */
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
