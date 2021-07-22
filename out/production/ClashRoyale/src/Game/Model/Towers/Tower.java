package Game.Model.Towers;

import Game.Controller.GameController;
import Game.Model.*;

public abstract class Tower extends Fightable {

    public Tower(Board board, int hp, int damage, long hitSpeed, double range, Location location, Team team, GameController controller) {
        super(board, hp, damage, hitSpeed, range, location, team, Type.BUILDING);
    }
}
