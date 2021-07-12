package Game.Model.Towers;

import Game.Model.*;

public abstract class Tower extends Fightable {

    public Tower(Board board, int hp, int damage, double hitSpeed, double range, Location location, Team team) {
        super(board, hp, damage, hitSpeed, range, location, team, Type.BUILDING);
    }
}
