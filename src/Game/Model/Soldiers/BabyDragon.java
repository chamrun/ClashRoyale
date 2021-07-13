package Game.Model.Soldiers;

import Game.Model.*;

import java.util.LinkedList;
import java.util.TimerTask;

public class BabyDragon extends Soldier {


    public BabyDragon(Board board, Level level, Location location, Team team) {
        super(board, getHP(level), getDamage(level), 1800, 3, location, Speed.FAST,
                Target.GROUND_AIR, true, 1, 4, team, Type.AIR);

        start();
    }

    @Override
    public int getCost() {
        return super.cost;
    }


    @Override
    public void live() {
        LinkedList<Fightable> target = new LinkedList<>();
        target.add(getNearestEnemy(board.getSearchFightableRange()));
        TimerTask move = new TimerTask() {
            @Override
            public void run() {
                if (!alive)
                    return;
                if (location.getDistance(target.get(0).getLocation()) <= range) {
                    fight(target);
                } else {
                    Location dest = target.get(0).getLocation();
                    move(dest);
                }

            }
        };
        moveTimer.schedule(move, 0, moveTime);
    }

    @Override
    public Fightable getNearestEnemy(double range) {
        double min = range;
        Fightable nearestEnemy = null;
        LinkedList<Fightable> enemy = (this.team.equals(Team.A)) ? board.getBFightables() : board.getAFightables();
        for (Fightable fightable : enemy) {
            if (this.location.getDistance(fightable.getLocation()) < min) {
                if (isValidEnemy(fightable)) {
                    nearestEnemy = fightable;
                    min = this.location.getDistance(fightable.getLocation());
                }
            }
        }
        return nearestEnemy;
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
