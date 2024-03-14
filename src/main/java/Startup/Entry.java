package Startup;

import API.APIservice;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Entry extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Entry.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        LoginPageController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setOnCloseRequest(event -> {
            System.out.println("Closing application...");
            APIservice.closeSocket();
            Platform.exit();
        });

        stage.setTitle("Welcome to login page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}