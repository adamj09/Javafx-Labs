package javafx_labs.lab_03_02;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Adam Johnston 2332003
 *         A class that allows for the display of an analog clock.
 *         This class was taken from 'Introduction to Java Programming and Data
 *         Structures' by Liang,
 *         and modified for learning purposes.
 */
public class ClockPane extends Pane {
    private int hour, minute, second;

    /**
     * Constructs a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
    }

    /**
     * Constructs a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets a new hour
     */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /**
     * @return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets a new minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /**
     * @return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Sets a new second
     */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /**
     * Paints the clock
     */
    private void paintClock() {
        // Initialize clock parameters
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double hourTickLength = clockRadius * 0.05;
        double minuteTickLength = clockRadius * 0.025;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);

        getChildren().clear(); // Clear the pane
        getChildren().addAll(circle, sLine, mLine, hLine); // Draw numbers and clock hands

        // Draw hour ticks and numbers
        for (int number = 12, tick = 0; number > 0 && tick < 12; number--, tick++) {
            // Numbers around the clock
            Text numberText = new Text(number + "");
            numberText.setFont(Font.font("Consolas", FontWeight.NORMAL, FontPosture.REGULAR, 12));
            numberText.setX(centerX - ((clockRadius - hourTickLength * 2) * Math.sin(Math.toRadians(30 * tick)))
                    - numberText.getLayoutBounds().getWidth() / 2);
            numberText.setY(centerY - ((clockRadius - hourTickLength * 2) * Math.cos(Math.toRadians(30 * tick)))
                    + numberText.getLayoutBounds().getHeight() / 4);

            // Hour ticks around the clock
            Line tickLine = new Line();
            tickLine.setStartX(centerX - ((clockRadius) * Math.sin(Math.toRadians(30 * tick))));
            tickLine.setStartY(centerY - ((clockRadius) * Math.cos(Math.toRadians(30 * tick))));
            tickLine.setEndX(tickLine.getStartX() + hourTickLength * Math.sin(Math.toRadians(30 * tick)));
            tickLine.setEndY(tickLine.getStartY() + hourTickLength * Math.cos(Math.toRadians(30 * tick)));

            getChildren().addAll(tickLine, numberText);
        }

        // Draw minute ticks
        for (int i = 0; i < 60; i++) {
            Line tickLine = new Line();
            tickLine.setStartX(centerX - ((clockRadius) * Math.sin(Math.toRadians(6 * i))));
            tickLine.setStartY(centerY - ((clockRadius) * Math.cos(Math.toRadians(6 * i))));
            tickLine.setEndX(tickLine.getStartX() + minuteTickLength * Math.sin(Math.toRadians(6 * i)));
            tickLine.setEndY(tickLine.getStartY() + minuteTickLength * Math.cos(Math.toRadians(6 * i)));
            getChildren().add(tickLine);
        }
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}
