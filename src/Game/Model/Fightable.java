package Game.Model;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Fightable extends Thread{
    protected Board board;
    protected boolean alive;
    protected int hp;
    protected int damage;
    protected double hitSpeed;
    protected final double range;
    protected final Location location;
    protected final Team team;
    protected final FightableType type;
//    private boolean isAlive;

    public Fightable(Board board, int hp, int damage, double hitSpeed, double range, Location location, Team team, FightableType type) {
        this.team = team;
        this.type = type;
        this.alive = true;
        this.board = board;
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.location = location;
    }

    public void changeDamage(int damage) {
        this.damage += damage;
    }

    public Fightable getNearestEnemy( double range) {
        double min = range;
        Fightable nearestEnemy = null;

        LinkedList<Fightable> enemies = (team.equals(Team.A))? board.getBFightables() : board.getAFightables();
        for (Fightable enemy: enemies){
            if (location.getDistance(enemy.getLocation()) < min) {
                nearestEnemy = enemy;
                min = location.getDistance(enemy.getLocation());
            }
        }
        return nearestEnemy;
    }

    public void changeHitSpeed(double hitSpeed) {
        this.hitSpeed += hitSpeed;
    }

    public FightableType getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    public void toGetHurt(int damage){
        hp -= damage;
        if (hp < 0){
            alive = false;
            board.removeFightable(this);
        }
    }


    public boolean alive() {
        return alive;
    }

    public void endamage(Fightable fightable){
        if (fightable != null)
            fightable.toGetHurt(damage);
    }

    public Location getLocation() {
        return location;
    }
}
