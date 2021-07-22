package Game.Model.Towers;

import Game.Controller.GameController;
import Game.Model.*;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Tower extends Fightable {

    public Tower(Board board, int hp, int damage, long hitSpeed, double range, Location location, Team team, GameController controller) {
        super(board, hp, damage, hitSpeed, range, location, team, Type.BUILDING);
        progressBar = new ProgressBar();
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
    }
}
