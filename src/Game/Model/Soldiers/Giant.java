package Game.Model.Soldiers;

import Game.Controller.GameController;
import Game.Model.*;

public class Giant extends Soldier {

    public Giant(Board board, Level level, Location location, Team team, GameController controller) {
        super(board, getHP(level), getDamage(level), 1500, 2, location, Speed.SLOW,
                Target.BUILDINGS, false, 1, 5, team , Type.GROUND,controller);

        start();
    }


    @Override
    public int getCost() {
        return super.cost;
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 2000;
            case TWO -> hp = 2200;
            case THREE -> hp = 2420;
            case FOUR -> hp = 2660;
            case FIVE -> hp = 2920;

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
            case ONE -> damage = 126;
            case TWO -> damage = 138;
            case THREE -> damage = 152;
            case FOUR -> damage = 167;
            case FIVE -> damage = 183;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
