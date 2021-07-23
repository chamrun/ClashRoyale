package Game.Model.Soldiers;

import Game.Controller.GameController;
import Game.Model.*;
import javafx.scene.image.ImageView;

public class Barbarian extends Soldier {

    protected ImageView walk_parallel_r;
//    protected ImageView walk_closed_r;
//    protected ImageView walk_open_r;


    public Barbarian(Board board, Level level, Location location, Team team, GameController controller) {
        super(board, getHP(level), getDamage(level), 1500, 2, location, Speed.MEDIUM,
                Target.GROUND, false, 4, 5, team , Type.GROUND,controller);


        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 300;
            case TWO -> hp = 330;
            case THREE -> hp = 363;
            case FOUR -> hp = 438;
            case FIVE -> hp = 480;

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
            case ONE -> damage = 75;
            case TWO -> damage = 82;
            case THREE -> damage = 90;
            case FOUR -> damage = 99;
            case FIVE -> damage = 109;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
