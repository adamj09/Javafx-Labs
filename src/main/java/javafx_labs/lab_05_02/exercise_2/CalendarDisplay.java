package javafx_labs.lab_05_02.exercise_2;

import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CalendarDisplay extends Application {
        private GregorianCalendar displayDate;
        private GridPane daysPane = new GridPane();
        private Label top = new Label();

        @Override
        public void start(Stage primaryStage) {
                displayDate = new GregorianCalendar();

                // Alignment
                daysPane.setAlignment(Pos.CENTER);
                daysPane.setHgap(10);
                daysPane.setVgap(10);

                Button previousButton = new Button("Previous"), nextButton = new Button("Next");

                previousButton.addEventHandler(ActionEvent.ACTION, _ -> {
                        displayDate.set(GregorianCalendar.MONTH, displayDate.get(GregorianCalendar.MONTH) - 1);
                        drawCalendar();
                });

                nextButton.addEventHandler(ActionEvent.ACTION, _ -> {
                        displayDate.set(GregorianCalendar.MONTH, displayDate.get(GregorianCalendar.MONTH) + 1);
                        drawCalendar();
                });

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
                        GregorianCalendar date = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR),
                                        displayDate.get(GregorianCalendar.MONTH), day);

                        daysPane.add(new Label(date.get(GregorianCalendar.DAY_OF_MONTH) + ""),
                                        date.get(GregorianCalendar.DAY_OF_WEEK) - 1,
                                        date.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY ? row++ : row);
                }

                GregorianCalendar firstDayOfDisplayMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR), displayDate.get(GregorianCalendar.MONTH), 1),
                                lastDayOfDisplayMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR), displayDate.get(GregorianCalendar.MONTH), displayDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
                
                GregorianCalendar previousMonth = new GregorianCalendar(displayDate.get(GregorianCalendar.YEAR), displayDate.get(GregorianCalendar.MONTH) - 1, 1);
                previousMonth.set(GregorianCalendar.DAY_OF_MONTH, previousMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - firstDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK) + 1);

                for(int i = 1; i < firstDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK); i++) {
                        daysPane.add(new Label((previousMonth.get(GregorianCalendar.DAY_OF_MONTH) + i) + ""), i - 1, 1);
                }
                
                // Day 1 is always the first day of the month; no need for calendar object.
                for(int day = 1, i = lastDayOfDisplayMonth.get(GregorianCalendar.DAY_OF_WEEK); i < GregorianCalendar.SATURDAY; i++) {
                        daysPane.add(new Label(day++ + ""), i, row);
                }
        }

        public static void main(String[] args) {
                launch(args);
        }
}
