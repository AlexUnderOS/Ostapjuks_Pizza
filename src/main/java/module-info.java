module com.alexosta.ostapjuks_pizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
<<<<<<< HEAD
    requires json.simple;


    opens com.alexosta.ostapjuks_pizza to javafx.fxml;
    exports com.alexosta.ostapjuks_pizza;
=======
    requires java.desktop;


    opens com.alexosta.ostapjuks_pizzeriaRegister to javafx.fxml;
    exports com.alexosta.ostapjuks_pizzeriaRegister;
    exports com.alexosta.ostapjuks_pizzeriaRegister.controller;
    opens com.alexosta.ostapjuks_pizzeriaRegister.controller to javafx.fxml;
    exports com.alexosta.ostapjuks_pizzeriaRegister.view;
    opens com.alexosta.ostapjuks_pizzeriaRegister.view to javafx.fxml;
    exports com.alexosta.ostapjuks_pizzeriaRegister.animations;
    opens com.alexosta.ostapjuks_pizzeriaRegister.animations to javafx.fxml;
    exports com.alexosta.ostapjuks_pizzeriaRegister.model;
    opens com.alexosta.ostapjuks_pizzeriaRegister.model to javafx.fxml;
>>>>>>> feat/db
}