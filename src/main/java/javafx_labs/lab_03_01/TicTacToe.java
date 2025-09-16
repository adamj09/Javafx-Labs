package javafx_labs.lab_03_01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Adam Johnston 2332003
 * A class that randomly fills out a tic-tac-toe board with
 * Xs, Os, and blank spaces.
 */
public class TicTacToe extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // ImageViews for the board (3x3 2D array).
        ImageView[][] imageViews = new ImageView[3][3];

        GridPane gridPane = new GridPane();

        // Randomly set blank spaces, Xs, and Os in the 3x3 grid (image links can be replaced by anything desired).
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                switch ((int)(Math.random() * 3)) {
                    case 0: // Fill space with an X.
                        imageViews[i][j] = new ImageView(new Image("https://media.tenor.com/OdB60pI5DO4AAAAM/letter-x.gif"));
                        break;
                    case 1: // Fill space with an O.
                        imageViews[i][j] = new ImageView(new Image("https://media.tenor.com/doRV9tAq3U0AAAAM/dancing-letter.gif"));
                        break;
                    case 2: // Do nothing; make space blank.
                        continue;
                }
                gridPane.add(imageViews[i][j], i, j); // Add the newly created ImageView to the gridPane.
            }
        }

        // Create restraints for rows and columns (to make sure they're square).
        // Since the board is 3x3, the width and height of each square should take up 1/3 of the board.
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100/3.0);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100/3.0);

        // Apply restraints to gridPane.
        gridPane.getColumnConstraints().addAll(columnConstraints);
        gridPane.getRowConstraints().addAll(rowConstraints);

        // Draw the final result.
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false); // Make sure the window is not resizable to prevent distortions to the board's shape.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
