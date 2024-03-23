package Creators;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class SceneBuilder {

    private final FXMLLoader fxmlLoader;
    private double width = 400;
    private double height = 600;

    public SceneBuilder(URL fxml){

        fxmlLoader = new FXMLLoader(fxml);
       }

    public SceneBuilder setSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    public Scene build() throws IOException {
        return new Scene(fxmlLoader.load(), width, height);
    }

    public Object getController(){
        return fxmlLoader.getController();
    }
}
