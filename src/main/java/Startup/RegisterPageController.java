package Startup;

import API.APIservice;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterPageController {

    private Stage currentStage;
    private Stage parentStage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private TextField phoneNumField;

    @FXML
    private Label errorLabel;


    public void setStages(Stage currentStage, Stage parentStage){
        this.currentStage = currentStage;
        this.parentStage = parentStage;
    }

    @FXML
    public void initialize(){
        errorLabel.setVisible(false);
    }

    @FXML
    private void backButtonPressed(){
        currentStage.close();
        parentStage.show();
    }

    @FXML
    private void registerButtonPressed(){

        if(!passwordField.getText().equals(passwordField2.getText()) || passwordField.getText().length() < 1){
            new Thread(() -> {
                Platform.runLater(() -> {
                    errorLabel.setText("Passwords do not match");
                    errorLabel.setVisible(true);
                });

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Sleeping interrupted :(");
                }
                Platform.runLater(() -> errorLabel.setVisible(false));

            }).start();
            return;
        }

        String res = APIservice.sendRegisterRequest(
                nameField.getText(),
                lastNameField.getText(),
                usernameField.getText(),
                passwordField.getText(),
                phoneNumField.getText()
        );

        System.out.println(res);
        new Alert(Alert.AlertType.INFORMATION,res, ButtonType.FINISH).show();
    }


}
