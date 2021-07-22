package Game.View;

import Debugging.Deb;
import Debugging.TestFighter;
import Game.Controller.GameController;
import Game.Model.Board;
import Game.Model.Fightable;
import Game.Model.Soldiers.Soldier;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;


public class GameView {

    //    private Board board;
    private final double landWidth = 976;
    private final double landHeight = landWidth * 9.0 / 16;
    private final double tileHeight = landHeight / 18;
    private final double tileWidth = landWidth / 35;
    private final int progressStandard = 1;
    private Pane pane;
    private LinkedList<TestFighter> fighters = new LinkedList<>();
    private TestFighter fighter;
    private GameController controller;

    public void setFighter(TestFighter fighter) {
        this.fighter = fighter;
    }

    public GameView(GameController controller) {
        this.controller = controller;
    }

    public void update() {

        if (fighters.size() == 0)
            return;
        fighters.get(0).getCurrentImage().setLayoutX(tileWidth+fighters.get(0).getCurrentImage().getLayoutX());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.removeElement(fighters.get(0).getCurrentImage());
        fighters.remove(0);

        //todo : clear pane
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                pane.getChildren().clear();
//                Deb.print("The screen was cleared.");
//            }
//        });
//        if (fighters.size() == 0)
//            return;
//        ImageView curr = fighter.getCurrentImage();
//        curr.setX(fighter.getLocation().getX() * tileWidth);
//        curr.setY(fighter.getLocation().getY() * tileHeight);
//        Deb.print("new position of" + fighter.getClass().toString()
//                + " on screen :  X = " + curr.getX() + " , Y = " + curr.getY() + " , Direction = ");
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                pane.getChildren().clear();
//
//            }
//        });
//        pane.getChildren().clear();

//
//        Deb.print("The screen was cleared.");
//
//        LinkedList<Fightable> fightables = board.getAFightables();
//        setFightableImages(fightables);
//
//        fightables = board.getBFightables();
//        setFightableImages(fightables);
//        setFightableImages(fighters);

        //todo : update elixirs


        // check fightables and their modes and directions and set images in their location

    }

//    public void setFightableImages(LinkedList<Fightable> fightables){
//        for (Fightable fightable : fightables){
//            ImageView curr = fightable.getCurrentImage();
//            curr.setX(fightable.getLocation().getX()*tileWidth);
//            curr.setY(fightable.getLocation().getY()*tileHeight);
//            if (fightable instanceof Soldier){
//                //todo : setting location : direction and moveProgress and location
//                pane.getChildren().add(curr);
//            }else {
//                //todo : setting location
//                pane.getChildren().add(curr);
//            }
//            Deb.print("new position of"+ fightable.getClass().toString()
//                    +" on screen :  X = "+curr.getX()+" , Y = "+curr.getY()+" , Direction = "+ fightable.getDirection());
//        }
//    }

    public void setFightableImages(LinkedList<TestFighter> fightables) {
        for (TestFighter fightable : fightables) {
//            ImageView curr = fightable.getCurrentImage();
//            curr.setX(fightable.getLocation().getX()*tileWidth);
//            curr.setY(fightable.getLocation().getY()*tileHeight);
            //todo : setting location : direction and moveProgress and location
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ImageView curr = fightable.getCurrentImage();
                    curr.setX(fightable.getLocation().getX() * tileWidth);
                    curr.setY(fightable.getLocation().getY() * tileHeight);
                    pane.getChildren().add(curr);
                    Deb.print("new position of" + fightable.getClass().toString()
                            + " on screen :  X = " + curr.getX() + " , Y = " + curr.getY() + " , Direction = ");

                }
            });
//                pane.getChildren().add(curr);
//            Deb.print("new position of"+ fightable.getClass().toString()
//                    +" on screen :  X = "+curr.getX()+" , Y = "+curr.getY()+" , Direction = ");
        }
    }

    public LinkedList<TestFighter> getFighters() {
        return fighters;
    }

    public double getTileHeight() {
        return tileHeight;
    }

    public double getTileWidth() {
        return tileWidth;
    }
}
