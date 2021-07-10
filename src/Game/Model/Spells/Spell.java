package Game.Model.Spells;

import Game.Model.Board;
import Game.Model.Card;
import Game.Model.Location;

public abstract class Spell extends Thread implements Card{

    protected final double radius;
    protected final int cost;
    protected final Board board;
    protected final Location location;


    public Spell(double radius, int cost, Board board, Location location) {
        this.radius = radius;
        this.cost = cost;
        this.board = board;
        this.location = location;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
