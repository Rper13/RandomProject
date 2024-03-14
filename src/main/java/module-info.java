module com.example.chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens Startup to javafx.fxml;
    exports Startup;
}