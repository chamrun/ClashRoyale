package Game.Model.Spells;

import Game.Model.Board;
import Game.Model.Level;
import Game.Model.Location;

public class Fireball extends Spell{
    private int areaDamage;

    public Fireball( Board board, Location location,Level level) {
        super(2.5, 4, board, location);
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
