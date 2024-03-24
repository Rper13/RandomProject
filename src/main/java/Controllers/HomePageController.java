package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;

public class HomePageController {

    @FXML
    private TextFlow chatTextFlow;

    @FXML
    private TextField messageTextField;

    @FXML
    private Circle userProfileImage;

    @FXML
    private GridPane homePageGrid;
    @FXML
    private Button uploadPhotoButton;


    @FXML
    public void initialize(){

        try {
            Image image = new Image(HomePageController.class.getResource("/Images/modified.png").toExternalForm());
            userProfileImage.setFill(new ImagePattern(image));

            userProfileImage.getStyleClass().add("profile-pictures");

            homePageGrid.add(uploadPhotoButton,0,1);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
