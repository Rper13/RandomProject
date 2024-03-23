package Startup;

import API.APIservice;
import Creators.SceneBuilder;
import HomePage.HomePageController;
import javafx.fxml.FXML;
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
    private void LogInButton_Pressed() {

        String response = APIservice.sendLoginRequest(userNameField.getText(), passwordField.getText());
        System.out.println(response);
        if(response.equals("Success")){
            try {
                Scene scene = new SceneBuilder(HomePageController.class.getResource("home-page.fxml"))
                        .setSize(800, 500).build();
                currentStage.setScene(scene);
                currentStage.setTitle("Welcome inside");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            new Alert(Alert.AlertType.ERROR, "Wrong Credentials");
        }

    }

    @FXML
    private void RegisterButton_Pressed() throws IOException {

        currentStage.hide();

        SceneBuilder sceneBuilder =
                new SceneBuilder(LoginPageController.class.getResource("register-page.fxml")).
                        setSize(400,600);

        Stage registerStage = new Stage();
        registerStage.setScene(sceneBuilder.build());

        RegisterPageController controller = (RegisterPageController) sceneBuilder.getController();
        controller.setStages(registerStage, currentStage);

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