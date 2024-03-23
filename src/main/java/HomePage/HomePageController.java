package HomePage;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
    public void initialize(){

        try {
            Image image = new Image(HomePageController.class.getResource("/Images/modified.png").toExternalForm());

            userProfileImage.setStroke(Color.DARKCYAN);
            userProfileImage.setFill(new ImagePattern(image));
            userProfileImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.GREY));

            userProfileImage.getStyleClass().add("profile-pictures");

            System.out.println(userProfileImage.getStyle());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
