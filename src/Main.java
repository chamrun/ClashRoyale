import Game.Controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("Game/View/GameView1.fxml"));
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

        imageView.setFitHeight(30.5);
        imageView.setFitWidth(30.5);
        imageView1.setFitHeight(50);
        imageView1.setFitWidth(50);
        imageView2.setFitHeight(30.5);
        imageView2.setFitWidth(30.5);
        imageView.setX(50);
        imageView.setY(70);
        imageView1.setX(170);
        imageView1.setY(70);
        imageView2.setX(260);

//        imageView.setRotationAxis(new );
        ImageView imageView3 = new ImageView();
        imageView3.setLayoutX(0);
        imageView3.setLayoutY(0);
        imageView3.setFitWidth(50);
        imageView3.setFitHeight(50);
        Image image = new Image("characters/Barbarians/Bar_Walk_Closed.png");
        imageView3.setImage(image);
        controller.landPane.getChildren().add(imageView3);
        controller.landPane.getChildren().add(imageView1);
//        controller.landPane.getChildren().add(imageView);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
