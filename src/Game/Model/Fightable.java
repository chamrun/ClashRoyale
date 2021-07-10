package Game.Model;

public abstract class Fightable {
    protected boolean isAlive;
    private int hp;
    private final int damage;
    protected final double hitSpeed;
    private final double range;

    public Fightable(int hp, int damage, double hitSpeed, double range) {
        isAlive = true;
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
    }

    public void toGetHurt(int damage){
        hp -= damage;
        if (hp < 0){
            isAlive = false;
        }
    }

    public void endamage(Fightable fightable){
        fightable.toGetHurt(damage);
    }

}
