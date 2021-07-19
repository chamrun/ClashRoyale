package Debugging;

import Game.Model.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class TestFighter extends Thread{

    private ImageView currentImage;
    private Location location;

    public TestFighter(Location location) {
        this.location = location;
        currentImage = new ImageView(new Image("characters/MiniPEKKA/Min_Walk_Open.png"));
        currentImage.setFitHeight(70);
        currentImage.setFitWidth(70);

    }

    @Override
    public void run() {
        while (true){
        move(new Location(12,12));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}

    public void move(Location destination) {
//        mode = Mode.MOVE;
        if (destination.getX() == location.getX()) {
            if (destination.getY() > location.getY())
                location.setY(location.getY() + 1);
            else
                location.setY(location.getY() - 1);
        } else {
            if (destination.getX() > location.getX())
                location.setX(location.getX() + 1);
            else
                location.setX(location.getX() - 1);
        }
    }
    public ImageView getCurrentImage() {
        return currentImage;
    }



    public Location getLocation() {
        return location;
    }
}
