module com.example.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasypt;


    opens Startup to javafx.fxml;
    exports Startup;
    exports Other;
    opens Other to javafx.fxml;
}