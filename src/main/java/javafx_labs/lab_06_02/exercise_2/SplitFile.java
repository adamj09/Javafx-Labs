package javafx_labs.lab_06_02.exercise_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Program used to split a file into multiple smaller files.
 */
public class SplitFile extends Application {
    Button splitButton = new Button("Split File");
    TextArea infoArea = new TextArea();
    TextField srcPathField = new TextField(),
            splitCountField = new TextField();
    CheckBox keepFileExtensionBox = new CheckBox("Keep File Extension");

    @Override
    public void start(Stage primaryStage) {
        splitButton.addEventHandler(ActionEvent.ANY, _ -> {
            try {
                splitFile(srcPathField.getText(), Integer.parseInt(splitCountField.getText()),
                        keepFileExtensionBox.isSelected());
            } catch (NumberFormatException ex) {
                infoArea.clear();
                infoArea.setText("Error: Split count must be a positive integer!");
            }
        });

        primaryStage.setScene(setupScene());
        primaryStage.setTitle("Split File");
        primaryStage.show();
    }

    /**
     * Sets up the scene for display.
     * 
     * @return Scene to be displayed.
     */
    private Scene setupScene() {
        infoArea.setEditable(false);
        infoArea.setPrefHeight(100);

        Label srcPathLabel = new Label("Source filepath:"),
                splitCountLabel = new Label("Split count:");

        srcPathField.setPrefWidth(200);
        splitCountField.setPrefWidth(200);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(srcPathLabel, 0, 0);
        gridPane.add(srcPathField, 1, 0);
        gridPane.add(splitCountLabel, 0, 1);
        gridPane.add(splitCountField, 1, 1);
        gridPane.add(keepFileExtensionBox, 1, 2);
        gridPane.add(splitButton, 1, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(infoArea);

        borderPane.setPadding(new Insets(10, 10, 10, 10));

        return new Scene(borderPane, 400, 300);
    }

    /**
     * Splits a file into multiple smaller files using binary I/O.
     * 
     * @param filepath          absolute path to the source file.
     * @param numberOfPieces    number of smaller files to split the source file
     *                          into.
     * @param keepFileExtension determines whether to keep the source file's
     *                          extension when saving the smaller files. If set to
     *                          false, the newly created smaller files will have no
     *                          extension.
     */
    public void splitFile(String filepath, int numberOfPieces, boolean keepFileExtension) {
        infoArea.clear();

        File srcFile = new File(filepath);

        String extension = "";
        if (keepFileExtension) {
            // Get extension to append to split files.
            int index = filepath.lastIndexOf('.');
            if (index > 0) {
                extension = "." + filepath.substring(index + 1);
            }
        }

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(srcFile))) {
            // Number of bytes each smaller file should contain.
            int bytesPerFile = (int) ((srcFile.length() % numberOfPieces == 0 ? srcFile.length()
                    : ((srcFile.length() / numberOfPieces) + numberOfPieces)));

            // Create a new directory to store the split files.
            File dstDir = new File(srcFile.getParent() + "/dst");
            if (!dstDir.exists()) {
                dstDir.mkdir();
            }
            infoArea.appendText("Saving split files into " + dstDir.getAbsolutePath());

            // For each smaller file, read the number of bytes (specified by bytesPerFile)
            // from the larger file, then write those bytes to the smaller file.
            for (int i = 0; i < numberOfPieces; i++) {
                byte[] bytes = new byte[bytesPerFile];
                int bytesRead = inputStream.read(bytes);
                inputStream.mark(0);

                File dstFile = new File(dstDir.getPath() + "/dst" + i + extension);

                // If the destination file already exists, abort.
                if (dstFile.exists()) {
                    infoArea.appendText("\nError: Destination file " + dstFile.getAbsolutePath() + " already exists!");
                    return;
                }

                dstFile.createNewFile();

                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(dstFile));
                outputStream.write(bytes);
                infoArea.appendText("\n" + bytesRead + " bytes written to " + dstFile.getAbsolutePath());
                outputStream.close();
            }
        } catch (FileNotFoundException ex) {
            infoArea.appendText("Cannot find source file: " + filepath);
        } catch (IOException ex) {
            infoArea.appendText("IOException occurred.");
        }

        infoArea.appendText("\nDone!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
