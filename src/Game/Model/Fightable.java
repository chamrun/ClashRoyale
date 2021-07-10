package Game.Model;

public abstract class Fightable implements Runnable{
    protected Board board;
    protected boolean isAlive;
    private int hp;
    protected int damage;
    protected final double hitSpeed;
    private final double range;
    protected final Location location;

    public Fightable(Board board, int hp, int damage, double hitSpeed, double range, Location location) {
        isAlive = true;
        this.board = board;
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.location = location;
    }

    public void toGetHurt(int damage){
        hp -= damage;
        if (hp < 0){
            isAlive = false;
        }
    }

    public void endamage(Fightable fightable){
        if (fightable != null)
            fightable.toGetHurt(damage);
    }

    public Location getLocation() {
        return location;
    }
}
