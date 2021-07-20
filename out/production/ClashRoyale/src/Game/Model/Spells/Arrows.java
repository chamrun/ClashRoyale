package Game.Model.Spells;

import Game.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Arrows extends Spell {
    private int areaDamage;

    public Arrows(Board board, Location location, Level level, Team team) {
        super(4, 3, board, location, team);
        areaDamage = getAD(level);

        start();
    }



    @Override
    public ArrayList<Fightable> validFightables() {
        ArrayList<Fightable> targets = new ArrayList<>();
        LinkedList<Fightable> enemies = (this.team.equals(Team.A)) ? board.getBFightables() : board.getAFightables();

        for (Fightable fightable : enemies) {
            if (location.getDistance(fightable.getLocation()) <= radius)
                targets.add(fightable);
        }
        return targets;
    }

    @Override
    public void run() {
        ArrayList<Fightable> targets = validFightables();

        applyEffect(targets);
    }

    @Override
    public void applyEffect(ArrayList<Fightable> targets) {
        for (Fightable fightable : targets)
            fightable.toGetHurt(areaDamage);
    }

    private int getAD(Level level) {
        int ad;

        switch (level) {
            case ONE -> ad = 144;
            case TWO -> ad = 156;
            case THREE -> ad = 174;
            case FOUR -> ad = 189;
            case FIVE -> ad = 210;

            default -> {
                ad = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return ad;
    }
}
