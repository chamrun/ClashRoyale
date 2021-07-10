package Game.Model.Spells;

import Game.Model.Board;
import Game.Model.Level;
import Game.Model.Location;

public class Rage extends Spell{

    private int effect;
    private double duration;

    public Rage( Board board, Location location,Level level) {
        super(5, 3, board, location);
        effect = 40;
        duration = getDuration(level);

        start();
    }

    @Override
    public void run() {
        // needs board
    }

    private double getDuration(Level level){
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
