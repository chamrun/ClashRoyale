package Game.Model;

public abstract class Building extends Fightable{
    Target target;
    int lifeTime;
    int cost;

    public Building(int hp, int damage, double hitSpeed, double range, Target target, int lifeTime, int cost) {
        super(hp, damage, hitSpeed, range);
        this.target = target;
        this.lifeTime = lifeTime;
        this.cost = cost;
    }
}
