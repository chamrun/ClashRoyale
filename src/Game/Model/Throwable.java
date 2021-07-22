package Game.Model;

import Game.Controller.GameController;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Throwable extends Thread {
    private String type;
    private long time;
    private Location sLocation;
    private Location eLocation;
    private GameController controller;
    private ImageView imageView;

    public Throwable(String type, long time, Location sLocation, Location eLocation, GameController controller) {
        this.type = type;
        this.time = time;
        this.sLocation = sLocation;
        this.eLocation = eLocation;
        this.controller = controller;

        String address = new String();
        if (type.equals("Arrow")) {
            address = "Pix/Cards/Archer.png";
        } else {
            address = "";
        }
        imageView = new ImageView(new Image(address));
        setSizeAndAngle();
        controller.addElement(imageView);
    }

    @Override
    public void run() {
        try {
            double xChanges = controller.getGameView().getTileWidth() * (eLocation.getX() - sLocation.getX())/4;
            double yChanges = controller.getGameView().getTileWidth() * (eLocation.getY() - sLocation.getY())/4;
            for (int i = 0; i < 4; i++) {
                Thread.sleep(time / 4);
                prepareMoveImageView(xChanges, yChanges);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void prepareMoveImageView(double xChanges, double yChanges) {
        controller.removeElement(imageView);
        imageView.setX(imageView.getX() + xChanges);
        imageView.setY(imageView.getY() + yChanges);
        controller.addElement(imageView);
    }

    public void setSizeAndAngle() {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        double m = (eLocation.getY() - sLocation.getY()) * 1.0 / (eLocation.getX() - sLocation.getX());
        double angle = Math.atan(m) * 180 / Math.PI;
        if (eLocation.getY() - sLocation.getY() < 0) {
            angle += 180;
        }
        imageView.setRotationAxis(new Point3D(0,0,1));
        imageView.setRotate(angle);
        imageView.setX(sLocation.getX() * controller.getGameView().getTileWidth());
        imageView.setY(sLocation.getY()* controller.getGameView().getTileHeight());
    }
}
