package Game.Model.Soldiers;

import Game.Controller.GameController;
import Game.Model.*;

public class Valkyrie extends Soldier{
    public Valkyrie(Board board, Level level, Location location, Team team, GameController controller) {
        super(board, getHP(level), getDamage(level), 1500, 2, location, Speed.MEDIUM,
                Target.GROUND, true, 1, 4, team , Type.GROUND,controller);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }



    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 880;
            case TWO -> hp = 968;
            case THREE -> hp = 1064;
            case FOUR -> hp = 1170;
            case FIVE -> hp = 1284;

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
            case ONE -> damage = 120;
            case TWO -> damage = 132;
            case THREE -> damage = 145;
            case FOUR -> damage = 159;
            case FIVE -> damage = 175;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
