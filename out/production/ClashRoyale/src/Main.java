import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("Accounts/View/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Menu/Profile.fxml"));
        //primaryStage.getIcons().add(new Image("/path/to/stackoverflow.jpg"));

        primaryStage.setTitle("Login");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
