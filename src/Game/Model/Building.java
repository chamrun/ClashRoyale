package Game.Model;

public abstract class Building extends Fightable implements Card{
    protected final Target target;
    protected final int lifeTime;
    protected final int cost;


    public Building(Board board, int hp, int damage, double hitSpeed, double range, Target target, int lifeTime,
                    int cost, Location location , Team team ) {
        super(board, hp, damage, hitSpeed, range, location, team , FightableType.BUILDING);
        this.target = target;
        this.lifeTime = lifeTime;
        this.cost = cost;
    }

    public FightableType getType(){
        return null;
    }
    public Location getLocation() {
        return location;
    }
}
