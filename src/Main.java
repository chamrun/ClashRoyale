import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("Accounts/View/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Menu/MainMenu.fxml"));a  a


        primaryStage.getIcons().add(new Image("Pix/Accounts/icon.png"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));//, 1136, 639));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
