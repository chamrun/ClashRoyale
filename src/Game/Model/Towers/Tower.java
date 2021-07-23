package Game.Model.Towers;

import Debugging.a;
import Game.Controller.GameController;
import Game.Model.*;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Tower extends Fightable {

    protected GameController controller;

    public Tower(Board board, int hp, int damage, long hitSpeed, double range, Location location, Team team, GameController controller) {
        super(board, hp, damage, hitSpeed, range, location, team, Type.BUILDING);
        this.controller = controller;
        progressBar = new ProgressBar(0.99);
        controller.addElement(progressBar);
        Platform.runLater(() -> progressBar.setProgress(1));
        convertProgressBarToAppropriateSize();

        Image image = null;

        if (this instanceof King){
            if (team == Team.A)
                image = new Image("Tower/aKing.png");
            if (team == Team.B)
                image = new Image("Tower/bKing.png");
        }
        else if (this instanceof Queen){
            if (team == Team.A)
                image = new Image("Tower/aQueen.png");
            if (team == Team.B)
                image = new Image("Tower/bQueen.png");

        }
        currentImage = new ImageView(image);
        currentImage.setX(tileWidth * location.getX());
        currentImage.setY(tileHeight * location.getY());
        setOnRightLocationBar();

        a.a("Progress bar of " + getClass() + ": "  + progressBar.getProgress());
        progressBar.setProgress(0.98);
        Platform.runLater(()-> progressBar.setProgress(0.97));
        a.a("Progress bar of " + getClass() + ": "  + progressBar.getProgress() + "\n");
    }


    public void setProgressHP(double progress){
        progressBar.setProgress(progress);
    }

}
