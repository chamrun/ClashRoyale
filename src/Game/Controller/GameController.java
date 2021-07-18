package Game.Controller;

import Debugging.Deb;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

import java.util.TimerTask;
import java.util.Timer;


public class GameController {
    private Timer timer = new Timer();
    private final int FRAMES_PER_SECOND = 0;
    private boolean isTimeOver = false;
    private long gameTime = 3 * 60 * 1000;
    private boolean doubleElixir = false;
    private long elixirTime = 2 * 1000;


    @FXML
    public Pane landPane;

    @FXML
    public ImageView backgroundImage;

    public void startTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update();
                    }
                });
                Deb.print("View has updated. {in controller}");
            }
        };

        long frameTimeInMilliseconds = (long) (1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);

    }

    public void update() {

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

    public void addPlayersElixirs(){
        //add Elixirs to players
    }



    @FXML
    void clickMouseOnLandPane(MouseEvent event) {

        //todo : check validation of location and card then put it in
        Deb.print("Mouse clicked on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
    }

    @FXML
    void moveMouseOnLandPane(MouseEvent event) {

        //todo : check validation of location and card then highlight it.
        Deb.print("Mouse moved on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
    }

}
