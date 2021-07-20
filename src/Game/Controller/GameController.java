package Game.Controller;

import Debugging.Deb;
import Debugging.TestFighter;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Card;
import Game.Model.Fightable;
import Game.Model.Location;
import Game.Model.Soldiers.*;
import Game.Model.Spells.Arrows;
import Game.Model.Spells.Fireball;
import Game.Model.Spells.Rage;
import Game.View.GameView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.TimerTask;
import java.util.Timer;


public class GameController {
    private Location[][] locations;
    private HashMap<Image, String> cardImages;
    private Image chosenCard;
    private Timer timer = new Timer();
    private final int FRAMES_PER_SECOND = 1;
    private boolean isTimeOver = false;
    private long gameTime = 3 * 60 * 1000;
    private boolean doubleElixir = false;
    private long elixirTime = 2 * 1000;

    private GameView gameView;


    public GameController() {
        this.gameView = new GameView(this);
//        startTimer();

    }

    public void initialize(GameView gameView) {
//        this.gameView = new GameView(landPane);
//        startTimer();
    }

    public void die(Node node){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                landPane.getChildren().remove(node);
            }
        });
        Deb.print("deleting char");
    }

    @FXML
    private ProgressBar timeProgressBar;

    @FXML
    public Pane landPane;

    @FXML
    public ImageView backgroundImage;

    public void startTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                gameView.update();
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        if (landPane.getChildren().size() != 2)
//                            landPane.getChildren().get(2).setLayoutX(landPane.getChildren().get(2).getLayoutX() + 50);
//                    }
//                });
                Deb.print("View has updated. {in controller}");
            }
        };

        long frameTimeInMilliseconds = (long) (1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
        Deb.print("Timer task starts.");

    }

    public void update() {
//        gameView.update();


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                landPane.getChildren().get(2).setLayoutX(landPane.getChildren().get(2).getLayoutX() + 50);
//                testFighter.getCurrentImage().setLayoutY(event.getY());
//                landPane.getChildren().add(testFighter.getCurrentImage());
            }
        });
        //todo : update view
        //todo : check time limit
        //todo : check end of game
    }

    public void gameTimer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(gameTime * 2 / 3);
                    doubleElixir = true;
                    Deb.print("Double elixir mode is on.");
                    Thread.sleep(gameTime * 1 / 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        isTimeOver = true;
        //its a new thread

    }

    public void addElixir() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time;
                if (doubleElixir)
                    time = elixirTime / 2;
                else
                    time = elixirTime;
                try {
                    Thread.sleep(time);
                    addPlayersElixirs();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //its a new thread
    }

    public void addPlayersElixirs() {
        //add Elixirs to players
    }

    public void removeLandElement(ImageView imageView){
        landPane.getChildren().remove(imageView);
    }


    @FXML
    void clickMouseOnLandPane(MouseEvent event) {

        //todo : check validation of location and card then put it in
        Deb.print("Mouse clicked on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
        Location chosenLocation = locations[(int) (event.getX()/gameView.getTileWidth())][(int) (event.getY()/gameView.getTileHeight())];
        if (chosenCard != null){
            if (chosenLocation.isEmpty()){
                Card card = createCard(chosenLocation);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        landPane.getChildren().add(card.getCurrentImage());
                    }
                });
                Deb.print("Class : GameController | method : clickMouseOnLandPane " +
                        "| new card added to landPane.");
            }
        }

//        TestFighter testFighter = new TestFighter(new Location(event.getX() / gameView.getTileWidth(),
//                event.getY() / gameView.getTileHeight()));
////
//        gameView.getFighters().add(testFighter);
//        startTimer();
//        testFighter.start();
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//        testFighter.getCurrentImage().setLayoutX(event.getX());
//        testFighter.getCurrentImage().setLayoutY(event.getY());
//        landPane.getChildren().add(testFighter.getCurrentImage());
//            }
//        });
    }

    @FXML
    void moveMouseOnLandPane(MouseEvent event) {

        //todo : check validation of location and card then highlight it.
//        Deb.print("Mouse moved on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
    }

    public Card createCard(Location location){
        Card card = null;
        switch (cardImages.get(chosenCard)){
            case "Archers":
                card =  new Archers();
                break;
            case "BabyDragon":
                card = new BabyDragon();
                break;
            case "Barbarian":
                card = new Barbarian();
                break;
            case "Giant":
                card = new Giant();
                break;
            case "MiniPEKKA":
                card = new MiniPEKKA();
                break;
            case "Valkyrie":
                card = new Valkyrie();
                break;
            case "Wizard":
                card = new Wizard();
                break;
            case "Cannon":
                card = new Cannon();
                break;
            case "InfernoTower":
                card = new InfernoTower();
                break;
            case "Arrows":
                card = new Arrows();
                break;
            case "Fireball":
                card = new Fireball();
                break;
            case "Rage":
                card = new Rage();
                break;
        }
        Deb.print("Class : GameController | method : createCard | new card created.");
        return card;
    }

}
