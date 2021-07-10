package Game.Model;

public abstract class Tower extends Fightable{

    public Tower(Board board, int hp, int damage, double hitSpeed, double range, Location location) {
        super(board, hp, damage, hitSpeed, range, location);
    }
}
