package Game.Model;

public abstract class Building extends Fightable{
    private final Target target;
    protected final int lifeTime;
    protected final int cost;
    protected final Location location;


    public Building(int hp, int damage, double hitSpeed, double range, Target target, int lifeTime, int cost, Location location) {
        super(hp, damage, hitSpeed, range);
        this.target = target;
        this.lifeTime = lifeTime;
        this.cost = cost;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
