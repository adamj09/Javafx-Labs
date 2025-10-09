module javafx_labs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens javafx_labs.example to javafx.fxml;

    exports javafx_labs.example;

    opens javafx_labs.test to javafx.fxml;

    exports javafx_labs.test;

    opens javafx_labs.lab_03_01 to javafx.fxml;

    exports javafx_labs.lab_03_01;

    opens javafx_labs.lab_03_02 to javafx.fxml;

    exports javafx_labs.lab_03_02;

    opens javafx_labs.lab_04_01.exercise_1 to javafx.fxml;

    exports javafx_labs.lab_04_01.exercise_1;

    opens javafx_labs.lab_04_01.exercise_2 to javafx.fxml;

    exports javafx_labs.lab_04_01.exercise_2;

    opens javafx_labs.lab_04_02.exercise_1 to javafx.fxml;

    exports javafx_labs.lab_04_02.exercise_1;

    opens javafx_labs.lab_04_02.exercise_2 to javafx.fxml;

    exports javafx_labs.lab_04_02.exercise_2;

    opens javafx_labs.lab_05_01.exercise_1 to javafx.fxml;

    exports javafx_labs.lab_05_01.exercise_1;

    opens javafx_labs.lab_05_01.exercise_2 to javafx.fxml;

    exports javafx_labs.lab_05_01.exercise_2;

    opens javafx_labs.lab_05_01.exercise_3 to javafx.fxml;

    exports javafx_labs.lab_05_01.exercise_3;

    opens javafx_labs.lab_05_02.exercise_1 to javafx.fxml;

    exports javafx_labs.lab_05_02.exercise_1;

    opens javafx_labs.lab_05_02.exercise_2 to javafx.fxml;

    exports javafx_labs.lab_05_02.exercise_2;
}