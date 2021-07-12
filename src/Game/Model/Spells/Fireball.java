package Game.Model.Spells;

import Game.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Fireball extends Spell{
    private int areaDamage;

    public Fireball(Board board, Location location, Level level , Team team) {
        super(2.5, 4, board, location, team);
        areaDamage = getAD(level);


        start();
    }

    @Override
    public void run() {
        ArrayList<Fightable> targets = validFightables();

        applyEffect(targets);
        //again needs board
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
    public void applyEffect(ArrayList<Fightable> targets) {
        for (Fightable fightable : targets){
            /*
            if (fightable instanceof Soldier)
                ((Soldier) fightable).die();
            else

             */
                fightable.toGetHurt(areaDamage);
        }
    }

    private int getAD(Level level){
        int ad;

        switch (level) {
            case ONE -> ad = 325;
            case TWO -> ad = 357;
            case THREE -> ad = 393;
            case FOUR -> ad = 432;
            case FIVE -> ad = 474;

            default -> {
                ad = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return ad;
    }
}
