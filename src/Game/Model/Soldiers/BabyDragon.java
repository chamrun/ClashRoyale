package Game.Model.Soldiers;

import Game.Model.*;

public class BabyDragon extends Soldier {


    public BabyDragon(Board board, Level level, Location location) {
        super(board, getHP(level), getDamage(level), 1.8, 3, location, Speed.FAST,
                Target.GROUND_AIR, true, 1, 4);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }


    @Override
    public void live() {

    }

    @Override
    public void die() {

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
