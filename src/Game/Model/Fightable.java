package Game.Model;

public abstract class Fightable extends Thread{
    protected Board board;
    protected boolean alive;
    protected int hp;
    protected int damage;
    protected final double hitSpeed;
    protected final double range;
    protected final Location location;
    protected final Team team;
    protected final FightableType type;
//    private boolean isAlive;

    public Fightable(Board board, int hp, int damage, double hitSpeed, double range, Location location, Team team, FightableType type) {
        this.team = team;
        this.type = type;
        isAlive = true;
        this.board = board;
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.location = location;
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
