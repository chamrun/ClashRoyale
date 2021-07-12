package Game.Model.Soldiers;

import Game.Model.*;

import java.util.LinkedList;

public abstract class Soldier extends Fightable implements Card {
    protected final Speed speed;
    protected final Target target;
    protected final boolean isAreaSplash;
    protected final int count;
    protected final int cost;
    protected FighterMode mode;
//    protected final Team team;

    public Soldier(Board board, int hp, int damage, double hitSpeed, double range, Location location, Speed speed, Target target,
                   boolean isAreaSplash, int count, int cost, Team team, FightableType type) {
        super(board, hp, damage, hitSpeed, range, location, team, type);
        this.speed = speed;
        this.target = target;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.cost = cost;
//        this.team = team;
    }

    @Override
    public void run() {
        live();
        die();
    }


    public Fightable getNearestEnemy(double range) {
        double min = range;
        Fightable nearestEnemy = null;
        LinkedList<Fightable> enemy = (this.team.equals(Team.A)) ? board.getBFightables() : board.getAFightables();
        for (Fightable fightable : enemy) {
            if (this.location.getRegion().equals(fightable.getLocation().getRegion())) {
                if (this.location.getDistance(fightable.getLocation()) < min) {
                    if (isValidEnemy(fightable)) {
                        nearestEnemy = fightable;
                        min = this.location.getDistance(fightable.getLocation());
                    }
                }
            }
        }
        return nearestEnemy;
    }

    public void fight(Fightable target){
        while (target.alive()){
            endamage(target);
        }
    }

    public void move(Location destination) {
        if (destination.getX() == location.getX()) {
            if (destination.getY() > location.getY())
                location.setY(location.getY() + 1);
            else
                location.setY(location.getY() - 1);
        } else {
            if (destination.getX() > location.getX())
                location.setX(location.getX() + 1);
            else
                location.setX(location.getX() - 1);
        }
    }

    public Location getNearestBridge() {
        return null;
        //todo : implement this
    }

    public void live() {
        Fightable target = getNearestEnemy(board.getSearchFightableRange());

        if (target == null) {
            Location dest = getNearestBridge();
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

    public abstract void die();

    public boolean isValidEnemy(Fightable fightable) {
        return true;
    }

//    public Team getTeam() {
//        return team;
//    }

    public Location getLocation() {
        return location;
    }
}
