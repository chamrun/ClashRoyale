package Game.Model.Soldiers;

import Game.Controller.GameController;
import Game.Model.*;

public class BabyDragon extends Soldier {


    public BabyDragon(Board board, Level level, Location location, Team team, GameController controller) {
        super(board, getHP(level), getDamage(level), 1800, 3, location, Speed.FAST,
                Target.GROUND_AIR, true, 1, 4, team, Type.AIR,controller);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }




    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 800;
            case TWO -> hp = 880;
            case THREE -> hp = 968;
            case FOUR -> hp = 1064;
            case FIVE -> hp = 1168;

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
            case ONE -> damage = 100;
            case TWO -> damage = 110;
            case THREE -> damage = 121;
            case FOUR -> damage = 133;
            case FIVE -> damage = 146;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }


}
