package Game.Model;

public abstract class Fightable {
    private int hp;
    private final int damage;
    private final double hitSpeed;
    private final double range;

    public Fightable(int hp, int damage, double hitSpeed, double range) {
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
    }

    public void gettingHurt(int damage){
        hp -= damage;
    }

    public void hurt(Fightable fightable){
        fightable.gettingHurt(damage);
    }

}
