package Game.Model.Soldiers;

import Game.Model.*;

public class Wizard extends Soldier {
    public Wizard(Board board, Level level, Location location, Team team) {
        super(board, getHP(level), getDamage(level), 1700, 5, location, Speed.MEDIUM,
                Target.GROUND_AIR, true, 1, 5, team , Type.GROUND);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }


    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 340;
            case TWO -> hp = 374;
            case THREE -> hp = 411;
            case FOUR -> hp = 452;
            case FIVE -> hp = 496;

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
            case ONE -> damage = 130;
            case TWO -> damage = 143;
            case THREE -> damage = 157;
            case FOUR -> damage = 172;
            case FIVE -> damage = 189;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
