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

        if(nameField.getText().length() < 2){
            errorLabel("Please enter your name");
            return;
        }else if(lastNameField.getText().length() < 2){
            errorLabel("Please enter your last name");
            return;
        }else if(usernameField.getText().length() < 4){
            errorLabel("Username must contain at least 4 characters");
            return;
        }else if(!passwordField.getText().equals(passwordField2.getText()) || passwordField.getText().length() < 1){
            errorLabel("Passwords do not match");
            return;
        } else if(phoneNumField.getText().matches(".*[^1-9].*")){
            errorLabel("Phone number has to contain only numbers");
            return;
        }else if(phoneNumField.getText().length() < 9){
            errorLabel("Enter your phone number without (+995). just 9 digits.");
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
        if(res.equals("Successfully Registered")){
            currentStage.close();
            parentStage.show();
        }
        new Alert(Alert.AlertType.INFORMATION, res , ButtonType.FINISH).show();
    }

    private void errorLabel(String errorText){
        new Thread(() -> {
            Platform.runLater(() -> {
                errorLabel.setText(errorText);
                errorLabel.setVisible(true);
            });

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Sleeping interrupted :(");
            }
            Platform.runLater(() -> errorLabel.setVisible(false));

        }).start();
    }

    @FXML
    private void fieldAction(){

        registerButtonPressed();

    }

}
