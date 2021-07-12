package Game.Model.Spells;

import Game.Model.*;

import java.util.ArrayList;

public abstract class Spell extends Thread implements Card{

    protected final double radius;
    protected final int cost;
    protected final Board board;
    protected final Location location;
    protected final Team team;


    public Spell(double radius, int cost, Board board, Location location, Team team) {
        this.radius = radius;
        this.cost = cost;
        this.board = board;
        this.location = location;
        this.team = team;
    }



    public abstract void applyEffect(ArrayList<Fightable> targets);

    public abstract ArrayList<Fightable>  validFightabales();

    @Override
    public int getCost() {
        return cost;
    }
}
