package Game.Model.Spells;

import Game.Model.*;
import Game.Model.Buildings.Building;
import Game.Model.Towers.Tower;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rage extends Spell {

    private int effect;
    private long duration;

    public Rage(Board board, Level level, Location location, Team team) {
        super(5, 3, board, location, team);
        effect = 40;
        duration = getDuration(level);

        start();
    }

    @Override
    public void run() {
        ArrayList<Fightable> targets = validFightables();
        for (Fightable fightable: targets) {
            fightable.rage(duration);
        }
        /*
        applyEffect(targets);
        try {
            Thread.sleep((long) (duration * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eliminateEffect(targets);

         */
    }



    @Override
    public void applyEffect(ArrayList<Fightable> fightables){
        for (Fightable fightable : fightables){
            fightable.changeDamage(effect);
            fightable.changeHitSpeed(effect);
            // speed ??????
        }
    }

    public void eliminateEffect(ArrayList<Fightable> fightables){
        for (Fightable fightable : fightables){
            fightable.changeDamage(-1 * effect);
            fightable.changeHitSpeed(-1 * effect);
            // speed ??????
        }
    }

    @Override
    public ArrayList<Fightable> validFightables(){
        ArrayList<Fightable> targets = new ArrayList<>();
        LinkedList<Fightable> fightables = (this.team.equals(Team.A)) ? board.getAFightables() : board.getBFightables();

        for (Fightable fightable : fightables){
            if (fightable instanceof Building || fightable instanceof Tower){
                if (location.getDistance(fightable.getLocation()) <= radius )
                    targets.add(fightable);
            }
        }
        return targets;
    }

    private long getDuration(Level level) {
        long duration;

        switch (level) {
            case ONE -> duration = 6000;
            case TWO -> duration = 6500;
            case THREE -> duration = 7000;
            case FOUR -> duration = 7500;
            case FIVE -> duration = 8000;

            default -> {
                duration = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return duration;
    }
}
