import Game.Controller.GameController;
import Game.Model.Board;
import Game.Model.Soldiers.Barbarian;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("Game/View/GameView.fxml"));
//        primaryStage.setTitle("Game");
//        primaryStage.setScene(new Scene(root));
        ImageView imageView = new ImageView(new Image("characters/Barbarians/Bar_Walk_Open.png"));
        ImageView imageView1 = new ImageView(new Image("characters/Barbarians/Bar_Walk_Closed.png"));
//        ImageView imageView3 = new ImageView(new Image("characters/Barbarians/Bar_Walk_Parallel.png"));

        imageView.setRotationAxis(new Point3D(0,1,0));
        imageView.setRotate(180);

        ImageView imageView2 = imageView;

//
//        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Game/View/GameView.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        GameController controller = loader.getController();

        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView1.setFitHeight(70);
        imageView1.setFitWidth(70);
        imageView2.setFitHeight(70);
        imageView2.setFitWidth(70);
        imageView.setX(50);
        imageView.setY(70);
        imageView1.setX(170);
        imageView1.setY(70);
        imageView2.setX(260);

//        imageView.setRotationAxis(new );
        controller.landPane.getChildren().add(imageView);
        controller.landPane.getChildren().add(imageView1);
//        controller.landPane.getChildren().add(imageView);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
