package Game.View;

import Debugging.Deb;
import Game.Model.Board;
import Game.Model.Fightable;
import Game.Model.Soldiers.Soldier;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameView {

    private Board board;
    private final double landLength = 480;
    private final double landWidth = landLength * 9.0 / 16;
    private final double tileLength = 0;
    private final double tileWidth = 0;
    private final int progressStandard = 1;
    private Pane pane;

    public void update(){

        //todo : clear pane

        Deb.print("The screen was cleared.");

        LinkedList<Fightable> fightables = board.getAFightables();
        setFightableImages(fightables);

        fightables = board.getBFightables();
        setFightableImages(fightables);

        //todo : update elixirs


        // check fightables and their modes and directions and set images in their location
        
    }

    public void setFightableImages(LinkedList<Fightable> fightables){
        for (Fightable fightable : fightables){
            ImageView curr = fightable.getCurrentImage();
            if (fightable instanceof Soldier){
                //todo : setting location : direction and moveProgress and location
                pane.getChildren().add(curr);
            }else {
                //todo : setting location
                pane.getChildren().add(curr);
            }
            Deb.print("new position of"+ fightable.getClass().toString()
                    +" on screen :  X = "+curr.getX()+" , Y = "+curr.getY()+" , Direction = "+ fightable.getDirection());
        }
    }






}
