package GlobalScope;

import Controllers.HomePageController;
import Creators.SceneBuilder;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Navigation {

    private static final HashMap<String, Scene> scenes = new HashMap<>();

    public static boolean addScene(String name, Scene scene){
        if(!scenes.containsKey(name)){
            scenes.put(name, scene);
            return true;
        }
        return false;
    }

    public static Scene getScene(String name){
        return scenes.get(name);
    }

    public static boolean deleteScene(String name){
        if(scenes.containsKey(name)){
            scenes.remove(name);
            return true;
        }
        return false;
    }

    public static void printScenes(){
        for(String sceneName : scenes.keySet()){
            System.out.print(sceneName + "; ");
        }
        System.out.println();
    }

    public static void goToHomePage(Stage currentStage){
        Scene scene;
        if(scenes.containsKey("Home Page")){
            scene = scenes.get("Home Page");
        }
        else{
            try {
                scene = new SceneBuilder(HomePageController.class.getResource("home-page.fxml"))
                        .setSize(800, 500)
                        .setCSS("/CSS/Node Styles.css")
                        .build();
                addScene("Home Page", scene);
            }catch (Exception e){
                System.out.println(e.getMessage());
                scene = null;
            }

        }
        currentStage.setScene(scene);
        currentStage.setTitle("Home Page");
    }

}
