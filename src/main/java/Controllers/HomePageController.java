package Controllers;

import GlobalScope.FXfunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private TextFlow chatTextFlow;

    @FXML
    private TextField messageTextField;

    @FXML
    private Circle userProfileCircle;

    @FXML
    private GridPane homePageGrid;
    @FXML
    private Button uploadPhotoButton;


    @FXML
    public void initialize(){

        try {
            Image image = new Image(HomePageController.class.getResource("/Images/modified.png").toExternalForm());

            userProfileCircle.setFill(new ImagePattern(image));
            userProfileCircle.getStyleClass().add("profile-pictures");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void uploadPhotoButtonClicked(){

        Image image = FXfunctions.uploadImage((Stage) uploadPhotoButton.getScene().getWindow());

        if(image == null) return;

        userProfileCircle.setFill(new ImagePattern(image));

    }

    @FXML
    private void messageTextFieldAction(){

        if(messageTextField.getText().equals("")) return;

        updateChatFlow(messageTextField.getText());
        messageTextField.setText("");

    }

    private void updateChatFlow(String message){

        ImagePattern imagePattern = (ImagePattern) userProfileCircle.getFill(); //getting image for circle

        Circle profPic = new Circle(10, imagePattern); //getting circle to display picture in chat

        Text text = new Text(message); //text itself

        HBox hBox = new HBox(profPic, text); //add both in HBox to be on the same liens


        chatTextFlow.getChildren().addAll(hBox, new Text("\n"));
    }

}
