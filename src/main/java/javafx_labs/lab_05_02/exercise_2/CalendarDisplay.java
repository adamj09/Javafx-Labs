package javafx_labs.lab_05_02.exercise_2;

import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalendarDisplay extends Application {
    private GregorianCalendar displayDate;
    private GridPane daysPane = new GridPane();
    private Label top = new Label();

    @Override
    public void start(Stage primaryStage) {
        displayDate = new GregorianCalendar();

        // Add days of the week above days of the month.
        daysPane.addRow(0, new Label("Sunday"), new Label("Monday"), new Label("Tuesday"), new Label("Wednesday"),
                new Label("Thursday"), new Label("Friday"), new Label("Saturday"));

        // Alignment
        daysPane.setAlignment(Pos.CENTER);
        daysPane.setHgap(10);
        daysPane.setVgap(10);

        BorderPane masterPane = new BorderPane(daysPane);
        masterPane.setTop(top);
        BorderPane.setAlignment(masterPane.getTop(), Pos.CENTER);
        masterPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(masterPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendar Display");
        primaryStage.show();

        drawCalendar();
    }

    private void drawCalendar() {
        // Set title (month, year).
        top.setText(displayDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault())
                + ", " + displayDate.get(GregorianCalendar.YEAR));

        // Fill in days for current month.
        for (int day = 1, row = 1; day <= displayDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); day++) {
            GregorianCalendar date = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                    displayDate.get(GregorianCalendar.MONTH), day);

            daysPane.add(new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + ""),
                    date.get(GregorianCalendar.DAY_OF_WEEK) - 1,
                    date.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY ? row++ : row);
        }

        // Fill in days for previous month.
        GregorianCalendar previousMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                displayDate.get(GregorianCalendar.MONTH) - 1, 1);
        previousMonth.set(GregorianCalendar.DAY_OF_MONTH, previousMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - (new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR), displayDate.get(GregorianCalendar.MONTH), 1)).get(GregorianCalendar.DAY_OF_WEEK) + 2);

        for (int day = previousMonth.get(GregorianCalendar.DAY_OF_MONTH); day <= previousMonth
                .getActualMaximum(GregorianCalendar.DAY_OF_MONTH); day++) {

            GregorianCalendar date = new GregorianCalendar(previousMonth.get(GregorianCalendar.YEAR),
                    previousMonth.get(GregorianCalendar.MONTH), day);

            // Label for the displaying the day of the month.
            Label label = new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + "");
            label.setStyle("-fx-text-fill: grey");
            label.setAlignment(Pos.CENTER);

            daysPane.add(label, date.get(GregorianCalendar.DAY_OF_WEEK) - 1, 1);
        }

        // Fill in days for next month.
        GregorianCalendar nextMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                displayDate.get(GregorianCalendar.MONTH) + 1, 1);
        nextMonth.set(GregorianCalendar.DAY_OF_MONTH, 1);

        for (int day = nextMonth.get(GregorianCalendar.DAY_OF_MONTH);; day++) {
            GregorianCalendar date = new GregorianCalendar(nextMonth.get(GregorianCalendar.YEAR),
                    nextMonth.get(GregorianCalendar.MONTH), day);

            // Label for the displaying the day of the month.
            Label label = new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + "");
            label.setStyle("-fx-text-fill: grey");
            label.setAlignment(Pos.CENTER);

            daysPane.add(label, date.get(GregorianCalendar.DAY_OF_WEEK) - 1, 5);

            if (date.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
