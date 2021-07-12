package Game.Model.Soldiers;

import Game.Model.*;

public class BabyDragon extends Soldier {


    public BabyDragon(Board board, Level level, Location location,Team team,FightableType type) {
        super(board, getHP(level), getDamage(level), 1.8, 3, location, Speed.FAST,
                Target.GROUND_AIR, true, 1, 4, team,type);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }


    @Override
    public void live() {
        Fightable target = getNearestEnemy(board.getSearchFightableRange());

        if (target == null) {
            Location dest = changeRegion();
            move(dest);
        } else {
            if (location.getDistance(target.getLocation()) <= range) {
                fight(target);
            } else {
                Location dest = target.getLocation();
                move(dest);
            }
        }
    }

    public Location changeRegion(){
        Location dest;
        if (location.getRegion().equals(Region.A)){
            dest = new Location(location.getX(), board.getLength()/2);
        }else {
            dest = new Location(location.getX(), board.getLength()/2 -1);
        }
        return dest;
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
