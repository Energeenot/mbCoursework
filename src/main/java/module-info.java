module com.example.mbcoursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mbcoursework to javafx.fxml;
    exports com.example.mbcoursework;
}