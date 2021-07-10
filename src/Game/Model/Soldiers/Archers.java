package Game.Model.Soldiers;

import Game.Model.*;

public class Archers extends Soldier {


    public Archers(Board board, Level level, Location location) {
        super(board, getHP(level), getDamage(level), 1.2, 5, location, Speed.MEDIUM,
                Target.GROUND_AIR, false, 2, 3);

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
            case ONE -> hp = 125;
            case TWO -> hp = 127;
            case THREE -> hp = 151;
            case FOUR -> hp = 166;
            case FIVE -> hp = 182;

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
            case ONE -> damage = 33;
            case TWO -> damage = 44;
            case THREE -> damage = 48;
            case FOUR -> damage = 53;
            case FIVE -> damage = 58;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
