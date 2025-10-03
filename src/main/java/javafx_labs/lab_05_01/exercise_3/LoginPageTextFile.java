package javafx_labs.lab_05_01.exercise_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

public class LoginPageTextFile extends Application {
    private Label loginInfoLabel = new Label(),
            verificationLabel = new Label();

    private TextField userField = new TextField(),
            passField = new TextField();

    private Button loginButton = new Button("Login");

    private ArrayList<String> usernames = new ArrayList<>(),
            passwords = new ArrayList<>(); // A hashmap would be better, rather than two ArrayLists

    @Override
    public void start(Stage primaryStage) {
        loginButton.addEventHandler(ActionEvent.ACTION, _ -> loginHandler());

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Login Page");
        primaryStage.show();

        loadLoginInfo();
        // Login info could not be found, abort.
        if (usernames.size() == 0 || passwords.size() == 0) {
            System.err.println("Login info could not be found!");
            primaryStage.close();
        }
    }

    private void loginHandler() {
        loginInfoLabel
                .setText("Username entered: " + userField.getText() + "\nPassword entered: " + passField.getText());

        boolean userValid = false;
        // Check if username exists and which username was given.
        int userIndex = 0;
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(userField.getText())) {
                userValid = true;
                userIndex = i;
                break;
            }
        }

        if (userValid) { // If the username does exist, check whether its associated password is
                         // correctly given.
            if (passwords.get(userIndex).equals(passField.getText())) {
                verificationLabel.setText("User is verified!");
            } else if (!passwords.get(userIndex).equals(passField.getText())) {
                verificationLabel.setText("Incorrect password!");
            }
        } else { // If the username does exist, state so.
            verificationLabel.setText("User does not exist!");
        }
    }

    private void loadLoginInfo() {
        File file = new File("src/main/resources/users.txt");
        try {
            Scanner scanner = new Scanner(file);
            // Clear arrays so that calling this method multiple times doesn't duplicate
            // info.
            usernames.clear();
            passwords.clear();

            while (scanner.hasNextLine()) {
                usernames.add(scanner.next());
                passwords.add(scanner.next());
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (NoSuchElementException ex) {
            // Clear arrays since the info wasn't properly formatted.
            usernames.clear();
            passwords.clear();
        }
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
