module com.alexosta.ostapjuks_pizza {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alexosta.ostapjuks_pizza to javafx.fxml;
    exports com.alexosta.ostapjuks_pizza;
}