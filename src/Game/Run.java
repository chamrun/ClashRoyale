package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("View/PlayGround.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Play!");
        Controller controller = loader.getController();
        root.setOnMouseClicked(controller);
        /*
        System.out.println(controller.getBoardHeight());
        double sceneWidth = controller.getBoardWidth() + 20.0;
        double sceneHeight = controller.getBoardHeight() + 70.0;

         */
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
