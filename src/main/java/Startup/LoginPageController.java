package Startup;

import API.APIservice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;

    private Stage currentStage;

    public void setStage(Stage currentStage){
        this.currentStage = currentStage;
    }

    @FXML
    public void initialize(){
        APIservice.Connect();
    }

    @FXML
    private void LogInButton_Pressed(){

        String s = APIservice.sendLoginRequest(userNameField.getText(), passwordField.getText());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(s);
        a.show();
    }

    @FXML
    private void RegisterButton_Pressed() throws IOException {

        currentStage.hide();

        Stage registerStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Entry.class.getResource("register-page.fxml"));
        registerStage.setScene(new Scene(fxmlLoader.load(), 400,600));

        RegisterPageController registerPageController = fxmlLoader.getController();
        registerPageController.setStages(registerStage, currentStage);

        registerStage.setTitle("Please Register");
        registerStage.show();

    }

    @FXML
    private void LogInField_Action(){
        if(passwordField.getText().length() > 0){
            LogInButton_Pressed();
        }
        else{
            passwordField.requestFocus();
        }

    }


}