package javafx_labs.lab_06_01.exercise_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SalarySaver extends Application {
    private HashMap<Integer, Double> salaries = new HashMap<>();
    private static SimpleIntegerProperty currentID = new SimpleIntegerProperty(0);
    private static Double total = 0.0;

    /**
     * Keep track of the number of saved salaries to prevent saving the same salary
     * twice.
     */
    private static Integer savedSalaryCount = 0;

    private ObservableList<Label> salaryLabels = FXCollections.observableArrayList();
    private Label errorLabel = new Label(), salaryCountLabel = new Label(), totalLabel = new Label(),
            averageSalaryLabel = new Label();
    private TextField salaryField = new TextField();
    private Button addSalaryButton = new Button("Add"),
            saveButton = new Button("Save");

    @Override
    public void start(Stage primaryStage) {
        load();

        addSalaryButton.addEventHandler(ActionEvent.ANY, _ -> {
            updateSalaryList();
            updateSalaryMetadata();
        });

        saveButton.addEventHandler(ActionEvent.ANY, _ -> save());

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Salary Saver");
        primaryStage.show();
    }

    private Scene setupScene() {
        salaryField.setPromptText("Enter Salary...");

        GridPane dataPane = new GridPane();
        dataPane.add(salaryCountLabel, 0, 0);
        dataPane.add(totalLabel, 0, 1);
        dataPane.add(averageSalaryLabel, 0, 2);

        ListView<Label> salaryListView = new ListView<>();
        salaryListView.setItems(salaryLabels);

        GridPane mainPane = new GridPane(10, 10);
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.add(salaryField, 0, 0);
        mainPane.add(addSalaryButton, 1, 0);
        mainPane.add(salaryListView, 0, 1);
        mainPane.add(dataPane, 1, 1);
        mainPane.add(errorLabel, 0, 2);
        mainPane.add(saveButton, 1, 2);

        return new Scene(mainPane, 450, 400);
    }

    private void updateSalaryList() {
        // Read new salary to add from TextField.
        Double newSalary = 0.0;
        try {
            newSalary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException ex) {
            errorLabel.setText("Enter a number!");
            return;
        }

        // Update list of salaries
        salaries.put(currentID.get(), newSalary);
        salaryLabels.add(new Label(currentID.get() + "\t$" + String.format("%.2f", salaries.get(currentID.get()))));

        // Update ID.
        currentID.set(currentID.get() + 1);

        // Update error label.
        if (errorLabel.getText() != null) {
            errorLabel.setText(null);
        }
    }

    private void updateSalaryMetadata() {
        // Update salary count.
        salaryCountLabel.setText("Salary count: " + (currentID.get()) + "");

        // Update total.
        for (int i = currentID.get() - 1; i < salaries.size(); i++) {
            total += salaries.get(i);
        }
        totalLabel.setText("Total: " + String.format("%.2f", total));

        // Update average.
        Double average = total / salaries.size();
        averageSalaryLabel.setText("Average salary: " + String.format("%.2f", average));
    }

    private void load() {
        File file = new File("src/main/resources/salaries.dat");
        if (!file.exists()) {
            return;
        }

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                salaries.put(inputStream.readInt(), inputStream.readDouble());
                salaryLabels.add(new Label(currentID.get() + "\t$" + String.format("%.2f", salaries.get(currentID.get()))));
                currentID.set(currentID.get() + 1);
            }
        } catch (EOFException ex) { // End of file reached: return.
            return;
        } catch (IOException ex) { // Abort and discard already loaded data.
            salaries.clear();
            return;
        }
    }

    private void save() {
        File file = new File("src/main/resources/salaries.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) { // Exception should never occur.
            }
        }

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file, true))) {
            int key = savedSalaryCount;
            // Append newly added salaries to the file.
            while (key < salaries.size()) {
                if(salaries.containsKey(key)) {
                    outputStream.writeInt(key);
                    outputStream.writeDouble(salaries.get(key));
                }
                key++;
            }
            savedSalaryCount = key;
        } catch (FileNotFoundException ex) { // Exceptions should never occur.
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
