package Game.Model.Buildings;

import Game.Controller.GameController;
import Game.Model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cannon extends Building {
    GameController controller;

    public Cannon(Board board, Level level, Location location , Team team, GameController controller){

        super(board, getHP(level), getDamage(level), 800, 5.5, Target.GROUND, 30,
                3, location,team,controller);
        setImage();
        setProgressBar();
        start();
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 380;
            case TWO -> hp = 418;
            case THREE -> hp = 459;
            case FOUR -> hp = 505;
            case FIVE -> hp = 554;

            default -> {
                hp = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return hp;
    }

    private static int getDamage(Level level) {
        int damage;
        switch (level) {
            case ONE -> damage = 60;
            case TWO -> damage = 66;
            case THREE -> damage = 72;
            case FOUR -> damage = 70;
            case FIVE -> damage = 87;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

    @Override
    public int getCost() {
        return super.cost;
    }



    @Override
    public void run() {

        while (alive){
            long start = System.currentTimeMillis();

            endamage(getNearestEnemy(range));

            try {
                Thread.sleep((int) (hitSpeed * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (start + super.lifeTime < System.currentTimeMillis()){
                return;
            }
        }
    }
}
