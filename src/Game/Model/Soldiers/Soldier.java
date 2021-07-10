package Game.Model.Soldiers;

import Game.Model.*;

public abstract class Soldier extends Fightable implements Card {
    protected final Speed speed;
    protected final Target target;
    protected final boolean isAreaSplash;
    protected final int count;
    protected final int cost;
    protected FighterMode mode;

    public Soldier(Board board, int hp, int damage, double hitSpeed, double range, Location location, Speed speed, Target target,
                   boolean isAreaSplash, int count, int cost) {
        super(board, hp, damage, hitSpeed, range, location);
        this.speed = speed;
        this.target = target;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.cost = cost;
    }

    public Location getLocation(){
        return location;
    }
}
