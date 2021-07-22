package Game.Model.Towers;

import Game.Controller.GameController;
import Game.Model.*;

public class Queen extends Tower {

    public Queen(Board board, Level level, Location location , Team team, GameController controller){
        super(board, getHP(level), getDamage(level), 800, 7.5, location , team, controller);

        currentImage.setFitHeight(92.7);
        currentImage.setFitWidth(56.55);
    }

    @Override
    public void run() {
        while (alive){

            endamage(getNearestEnemy( range));

            try {
                Thread.sleep((int) hitSpeed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 1400;
            case TWO -> hp = 1512;
            case THREE -> hp = 1624;
            case FOUR -> hp = 1750;
            case FIVE -> hp = 1890;

            default -> {
                hp = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return hp;
    }

    private static int getDamage(Level level) {
        int damage;
        switch (level) {
            case ONE -> damage = 50;
            case TWO -> damage = 54;
            case THREE -> damage = 58;
            case FOUR -> damage = 62;
            case FIVE -> damage = 69;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
