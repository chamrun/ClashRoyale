package Game.Model;

public abstract class Fightable {
    private int hp;
    private final int damage;
    private final int hitSpeed;
    private final int range;

    public Fightable(int hp, int damage, int hitSpeed, int range) {
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
