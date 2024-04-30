module com.alexosta.ostapjuks_pizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;


    opens com.alexosta.ostapjuks_pizza to javafx.fxml;
    exports com.alexosta.ostapjuks_pizza;
}