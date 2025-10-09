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

    @Override
    public void start(Stage primaryStage) {
        displayDate = new GregorianCalendar();

        daysPane.addRow(0, new Label("Sunday"), new Label("Monday"), new Label("Tuesday"), new Label("Wednesday"),
                new Label("Thursday"), new Label("Friday"), new Label("Saturday"));
        daysPane.setAlignment(Pos.CENTER);
        daysPane.setHgap(10);
        daysPane.setVgap(10);

        BorderPane masterPane = new BorderPane(daysPane);
        masterPane.setTop(
                new Label(displayDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault())
                        + ", " + displayDate.get(GregorianCalendar.YEAR)));
        BorderPane.setAlignment(masterPane.getTop(), Pos.CENTER);
        masterPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(masterPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendar Display");
        primaryStage.show();

        // Fill in days for current month.
        for (int i = 0, j = 0; i < displayDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
            GregorianCalendar date = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                    displayDate.get(GregorianCalendar.MONTH), i + 1);

            daysPane.add(new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + ""),
                    date.get(GregorianCalendar.DAY_OF_WEEK) - 1,
                    date.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY ? j++ + 1 : j + 1);
        }

        // Fill in days for previous month.

        // Fill in days for next month.
    }

    public static void main(String[] args) {
        launch(args);
    }
}
