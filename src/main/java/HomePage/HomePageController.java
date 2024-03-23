package HomePage;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class HomePageController {

    @FXML
    private TextFlow chatTextFlow;

    @FXML
    private TextField messageTextField;

    @FXML
    private ImageView userProfileImage;

    @FXML
    public void initialize(){

        try {
            Image image = new Image(HomePageController.class.getResource("/Images/modified.png").toExternalForm());
            userProfileImage.setImage(image);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
