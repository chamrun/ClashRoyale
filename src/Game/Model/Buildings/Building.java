package Game.Model.Buildings;

import Game.Controller.GameController;
import Game.Model.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Building extends Fightable implements Card {
    protected final Target target;
    protected final int lifeTime;
    protected final int cost;
    protected GameController controller;


    public Building(Board board, int hp, int damage, long hitSpeed, double range, Target target, int lifeTime,
                    int cost, Location location , Team team,GameController gameController) {
        super(board, hp, damage, hitSpeed, range, location, team , Type.BUILDING);
        this.target = target;
        this.lifeTime = lifeTime;
        this.cost = cost;
        controller = gameController;
        progressBar = new ProgressBar();
    }

    public Type getType(){
        return null;
    }
    public Location getLocation() {
        return location;
    }


    public void setProgressBar(){
        convertProgressBarToAppropriateSize();
        setOnRightLocationBar();
        controller.addElement(progressBar);
    }

    public void setImage(){
        String address;
        if (this instanceof Cannon)
            address = "Building/Cannon.png";
        else
            address = (team.equals(Team.A))? "Building/Inferno_A.png":"Building/Inferno_B.png";

        System.out.println("Setting pic for " + this);

        currentImage = new ImageView(new Image(address));
        currentImage.setFitHeight(50);
        currentImage.setFitWidth(50);
        currentImage.setX(location.getX() * controller.getGameView().getTileWidth());
        currentImage.setY(location.getY() * controller.getGameView().getTileHeight());
        controller.addElement(currentImage);
    }


}
