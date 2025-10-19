package javafx_labs.lab_06_01.exercise_2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class providing a GUI for the SalaryManager class.
 */
public class App extends Application {
    /**
     * Labels used to display the list of salaries.
     */
    private ObservableList<Label> salaryLabels = FXCollections.observableArrayList();

    /**
     * ListView used to display the salary labels.
     */
    private ListView<Label> salaryListView = new ListView<>();

    /**
     * General information about the application's status.
     */
    private Label infoLabel = new Label();

    /**
     * Metadata regarding the salaries.
     */
    private Label salaryCountLabel = new Label(), sumOfSalariesLabel = new Label(),
            averageSalaryLabel = new Label();

    /**
     * TextField used to enter a new salary.
     */
    private TextField salaryField = new TextField();

    /**
     * Buttons to add salary to the HashMap, save salary to the file, clear all
     * saved and unsaved salaries, and clear only unsaved salaries, respectively.
     */
    private Button addSalaryButton = new Button("Add"),
            saveButton = new Button("Save"), clearAllButton = new Button("Delete All"),
            clearUnsavedButton = new Button("Delete Unsaved");

    /**
     * Used to add, remove, save, and load salaries.
     */
    private SalaryManager manager;

    @Override
    public void start(Stage primaryStage) {
        manager = new SalaryManager("src/main/resources/salaries.dat");

        setupHandlers(primaryStage);

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Salary Manager");
        primaryStage.show();
    }

    /**
     * Sets up the scene for display.
     * 
     * @return Scene to be displayed.
     */
    private Scene setupScene() {
        infoLabel.setWrapText(true);
        salaryCountLabel.setWrapText(true);
        sumOfSalariesLabel.setWrapText(true);
        averageSalaryLabel.setWrapText(true);

        salaryField.setPromptText("Enter Salary...");

        GridPane dataPane = new GridPane();
        dataPane.add(salaryCountLabel, 0, 0);
        dataPane.add(sumOfSalariesLabel, 0, 1);
        dataPane.add(averageSalaryLabel, 0, 2);

        salaryListView.setItems(salaryLabels);
        salaryListView.setPrefWidth(300);

        FlowPane modifyPane = new FlowPane(saveButton, clearAllButton, clearUnsavedButton);
        modifyPane.setPadding(new Insets(10, 10, 10, 10));
        modifyPane.setHgap(10);

        GridPane mainPane = new GridPane(10, 10);
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.add(salaryField, 0, 0);
        mainPane.add(addSalaryButton, 1, 0);
        mainPane.add(salaryListView, 0, 1);
        mainPane.add(dataPane, 1, 1);
        mainPane.add(modifyPane, 0, 2);
        mainPane.add(infoLabel, 1, 2);

        displaySavedSalaries();

        return new Scene(mainPane, 650, 400);
    }

    /**
     * Sets up the event handlers for all the buttons.
     */
    private void setupHandlers(Stage stage) {
        // Add new salary when hitting "enter" in the text field.
        salaryField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addSalaryButton.fire();
            }
        });

        addSalaryButton.addEventHandler(ActionEvent.ANY, _ -> {
            try {
                addSalary();
                infoLabel.setText("Added new salary: ID: " + manager.getLatestID() + ", sum of $"
                        + String.format("%.2f", manager.getUnsavedSalariesMap().get(manager.getLatestID())));

            } catch (NumberFormatException ex) {
                infoLabel.setText("Enter a number!");
            }
        });

        saveButton.addEventHandler(ActionEvent.ANY, _ -> {
            manager.save();
            infoLabel.setText("Saved!");
        });

        clearAllButton.addEventHandler(ActionEvent.ANY, _ -> {
            manager.clearAll();
            updateMetadata();

            salaryListView.getItems().clear();
            infoLabel.setText("All salaries deleted!");
        });

        clearUnsavedButton.addEventHandler(ActionEvent.ANY, _ -> {
            manager.clearUnsaved();
            updateMetadata();

            salaryListView.getItems().clear();
            displaySavedSalaries();
            infoLabel.setText("All unsaved salaries deleted!");
        });
    }

    /**
     * Display all salaries loaded from the file.
     */
    private void displaySavedSalaries() {
        for (int i = 0; i < manager.getSavedSalariesMap().size(); i++) {
            if (manager.getSavedSalariesMap().containsKey(i)) {
                salaryLabels.add(new Label(i + "\t$" + String.format("%.2f", manager.getSavedSalariesMap().get(i))));
            }
        }
        updateMetadata();
    }

    /**
     * Add a salary to the HashMap of unsaved salaries. That HashMap serves as a
     * buffer, keeping these salaries until saved to the file (or deleted).
     */
    private void addSalary() throws NumberFormatException {
        // Read new salary from the TextField.
        Double newSalary = 0.0;
        try {
            newSalary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }

        // Add new salary to the HashMap.
        manager.addSalary(newSalary);

        // Add a new label to the list (must be called AFTER adding the new salary to
        // the HashMap).
        salaryLabels.add(new Label(manager.getLatestID() + "\t$"
                + String.format("%.2f", manager.getUnsavedSalariesMap().get(manager.getLatestID()))));

        updateMetadata();
    }

    /**
     * Updates the displayed salary count, sum, and average.
     */
    private void updateMetadata() {
        salaryCountLabel
                .setText("Salary count: "
                        + (manager.getUnsavedSalariesMap().size() + manager.getSavedSalariesMap().size()));
        sumOfSalariesLabel.setText("Sum of salaries: $" + String.format("%.2f", manager.getSumOfSalaries()));
        averageSalaryLabel.setText("Average salary: $" + String.format("%.2f", manager.getSumOfSalaries()
                / (manager.getUnsavedSalariesMap().size() + manager.getSavedSalariesMap().size())));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
