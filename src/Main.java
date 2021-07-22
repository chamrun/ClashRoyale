import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        URL url = getClass().getResource("Accounts/View/login.fxml");
        if (url == null){
            System.out.println("url is null!");
            return;
        }

        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("Menu/View/MainMenu.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Menu/View/NewGame.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Menu/View/Deck.fxml"));

        primaryStage.getIcons().add(new Image("Pix/Accounts/icon.png"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));//, 1136, 639));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
