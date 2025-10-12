package javafx_labs.lab_05_02.exercise_2;

import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Adam Johnston 2332003
 * 
 *         Class used to display a calendar showing the current month, with
 *         options to display the previous and next months infinitely backwards
 *         and forwards in time resptively.
 */
public class CalendarDisplay extends Application {
    private GregorianCalendar displayDate;
    private GridPane daysPane = new GridPane();
    private Label top = new Label();

    @Override
    public void start(Stage primaryStage) {
        // Initialize the date to today.
        displayDate = new GregorianCalendar();

        // Alig calendar to center of scene.
        daysPane.setAlignment(Pos.CENTER);
        daysPane.setHgap(10);
        daysPane.setVgap(10);

        // Set up buttons to show previous and next months.
        Button previousButton = new Button("Prior"), nextButton = new Button("Next");
        previousButton.addEventHandler(ActionEvent.ACTION, _ -> {
            displayDate.set(GregorianCalendar.MONTH, displayDate.get(GregorianCalendar.MONTH) - 1);
            drawCalendar();
        });
        nextButton.addEventHandler(ActionEvent.ACTION, _ -> {
            displayDate.set(GregorianCalendar.MONTH, displayDate.get(GregorianCalendar.MONTH) + 1);
            drawCalendar();
        });

        // Button tray
        HBox buttonBox = new HBox(previousButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        BorderPane masterPane = new BorderPane(daysPane);
        masterPane.setTop(top);
        masterPane.setBottom(buttonBox);

        BorderPane.setAlignment(masterPane.getTop(), Pos.CENTER);

        masterPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(masterPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendar Display");
        primaryStage.show();

        drawCalendar();
    }

    /**
     * Draws the days of the month into a Gridpane.
     */
    private void drawCalendar() {
        // Clear the gridpane to make room for displaying the month.
        daysPane.getChildren().clear();

        // Set title (month, year).
        top.setText(displayDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG,
                Locale.getDefault())
                + ", " + displayDate.get(GregorianCalendar.YEAR));

        daysPane.addRow(0, new Label("Sunday"), new Label("Monday"), new Label("Tuesday"),
                new Label("Wednesday"),
                new Label("Thursday"), new Label("Friday"), new Label("Saturday"));

        // Fill in days for current month.
        int row = 1;
        for (int day = 1; day <= displayDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); day++) {
            // The current date to draw.
            GregorianCalendar date = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                    displayDate.get(GregorianCalendar.MONTH), day);

            Label dayLabel = new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + "");

            // Add and center day to GridPane and.
            daysPane.add(dayLabel, date.get(GregorianCalendar.DAY_OF_WEEK) - 1,
                    date.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY ? row++ : row);
            GridPane.setHalignment(dayLabel, HPos.CENTER);
        }

        // The first day of the currently displayed month.
        GregorianCalendar firstDayOfDisplayMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                displayDate.get(GregorianCalendar.MONTH), 1),

                // The last day of the currently displayed month.
                lastDayOfDisplayMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                        displayDate.get(GregorianCalendar.MONTH),
                        displayDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

        // The month prior to the currently displayed month
        GregorianCalendar previousMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                displayDate.get(GregorianCalendar.MONTH) - 1, 1);
        // The last Sunday of the prior month.
        previousMonth.set(GregorianCalendar.DAY_OF_MONTH, previousMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
                - firstDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK) + 1);

        // Display days from previous month in grey.
        for (int i = 1; i < firstDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK); i++) {
            Label dayLabel = new Label((previousMonth.get(GregorianCalendar.DAY_OF_MONTH) + i) + "");
            dayLabel.setTextFill(Color.rgb(184, 184, 184, 1));

            daysPane.add(dayLabel, i - 1, 1);
            GridPane.setHalignment(dayLabel, HPos.CENTER);
        }

        // Display days from next month in grey.
        // Day 1 is always the first day of the month; no need for calendar object.
        for (int day = 1,
                i = lastDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK); i < GregorianCalendar.SATURDAY; i++) {
            Label dayLabel = new Label(day++ + "");
            dayLabel.setTextFill(Color.rgb(184, 184, 184, 1));
            dayLabel.setAlignment(Pos.CENTER);

            daysPane.add(dayLabel, i, row);
            GridPane.setHalignment(dayLabel, HPos.CENTER);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
