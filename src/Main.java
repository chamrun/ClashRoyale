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
        final ImageView[] imageView = {new ImageView(new Image("characters/Barbarians/Bar_Walk_Open.png"))};
        ImageView imageView1 = new ImageView(new Image("characters/Barbarians/Bar_Walk_Closed.png"));
//        ImageView imageView3 = new ImageView(new Image("characters/Barbarians/Bar_Walk_Parallel.png"));

        imageView[0].setRotationAxis(new Point3D(0,1,0));
        imageView[0].setRotate(180);

        ImageView imageView2 = imageView[0];

//
//        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("Game/View/GameView.fxml"));
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("Game/View/GameView.fxml"));
//        loader.load();
//        Parent root = loader.getRoot();
//        GameController controller = loader.getController();

        imageView[0].setFitHeight(30.5);
        imageView[0].setFitWidth(30.5);
        imageView1.setFitHeight(50);
        imageView1.setFitWidth(50);
        imageView2.setFitHeight(30.5);
        imageView2.setFitWidth(30.5);
        imageView[0].setX(50);
        imageView[0].setY(70);
        imageView1.setX(170);
        imageView1.setY(70);
        imageView2.setX(260);
        System.out.println((int) 6.79);

//        imageView.setRotationAxis(new );
//        ImageView imageView3 = new ImageView();
//        imageView3.setLayoutX(0);
//        imageView3.setLayoutY(0);
//        imageView3.setFitWidth(50);
//        imageView3.setFitHeight(50);
//        Image image = new Image("characters/Barbarians/Bar_Walk_Closed.png");
//        imageView3.setImage(image);
//        controller.landPane.getChildren().add(imageView3);
//        controller.landPane.getChildren().add(imageView1);
//        Board board = new Board(18,35,18);
//        controller.setBoard(board);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5*1000);
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                             imageView[0].setY(500);
//                             imageView[0] = imageView1;
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//        imageView = imageView3;
//        controller.landPane.getChildren().add(imageView[0]);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
