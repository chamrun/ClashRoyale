package Game.Model.Spells;

import Game.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rage extends Spell {

    private int effect;
    private double duration;

    public Rage(Board board, Location location, Level level,Team team) {
        super(5, 3, board, location, team);
        effect = 40;
        duration = getDuration(level);

        start();
    }

    @Override
    public void run() {
        ArrayList<Fightable> targets = validFightabales();
        applyEffect(targets);
        try {
            Thread.sleep((long) (duration * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eliminateEffect(targets);
    }

    public void eliminateEffect(ArrayList<Fightable> fightables){
        for (Fightable fightable : fightables){
            fightable.changeDamage(-1 * effect);
            fightable.changeHitSpeed(-1 * effect);
            // speed ??????
        }
    }

    @Override
    public void applyEffect(ArrayList<Fightable> fightables){
        for (Fightable fightable : fightables){
            fightable.changeDamage(effect);
            fightable.changeHitSpeed(effect);
            // speed ??????
        }
    }

    @Override
    public ArrayList<Fightable> validFightabales(){
        ArrayList<Fightable> targets = new ArrayList<>();
        LinkedList<Fightable> fightables = (this.team.equals(Team.A)) ? board.getAFightables() : board.getBFightables();

        for (Fightable fightable : fightables){
            if (fightable instanceof Building || fightable instanceof  Tower){
                if (location.getDistance(fightable.getLocation()) <= radius )
                    targets.add(fightable);
            }
        }
        return targets;
    }

    private double getDuration(Level level) {
        double duration;

        switch (level) {
            case ONE -> duration = 6;
            case TWO -> duration = 6.5;
            case THREE -> duration = 7;
            case FOUR -> duration = 7.5;
            case FIVE -> duration = 8;

            default -> {
                duration = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return duration;
    }
}
