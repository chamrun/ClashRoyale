package Game.Model;

public class Building extends Fightable{
    Target target;
    int lifeTime;
    int cost;

    public Building(int hp, int damage, int hitSpeed, int range, Target target, int lifeTime, int cost) {
        super(hp, damage, hitSpeed, range);
        this.target = target;
        this.lifeTime = lifeTime;
        this.cost = cost;
    }
}
