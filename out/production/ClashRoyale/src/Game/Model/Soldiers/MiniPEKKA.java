package Game.Model.Soldiers;

import Game.Model.*;

public class MiniPEKKA extends Soldier {

    public MiniPEKKA(Board board, Level level, Location location, Team team) {
        super(board, getHP(level), getDamage(level), 1800, 1, location, Speed.FAST,
                Target.GROUND, false, 1, 4, team , Type.GROUND);

        start();
    }


    @Override
    public int getCost() {
        return super.cost;
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 600;
            case TWO -> hp = 660;
            case THREE -> hp = 726;
            case FOUR -> hp = 798;
            case FIVE -> hp = 876;

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
            case ONE -> damage = 325;
            case TWO -> damage = 357;
            case THREE -> damage = 393;
            case FOUR -> damage = 432;
            case FIVE -> damage = 474;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
