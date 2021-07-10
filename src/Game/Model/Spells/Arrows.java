package Game.Model.Spells;

import Game.Model.Board;
import Game.Model.Level;
import Game.Model.Location;

public class Arrows extends Spell{
    private int areaDamage;

    public Arrows(Board board, Location location, Level level) {
        super(4, 3, board, location);
        areaDamage = getAD(level);


        start();
    }

    @Override
    public void run() {
        //again needs board
    }

    private int getAD(Level level){
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
