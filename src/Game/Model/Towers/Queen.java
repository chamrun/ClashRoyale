package Game.Model.Towers;

import Debugging.a;
import Game.Controller.GameController;
import Game.Model.*;

public class Queen extends Tower {

    public Queen(Board board, Level level, Location location , Team team, GameController controller){
        super(board, getHP(level), getDamage(level), 800, 7.5, location , team, controller);

        currentImage.setFitHeight(92.7);
        currentImage.setFitWidth(56.55);

        start();
    }

    @Override
    public void run() {

        a.a(team + " Queen started.");
        int i = 0;

        while (alive){

            //a.a(i + "th of" + team + " Queen");
            i++;
            Fightable enemy = getNearestEnemy( range);
            //endamage(enemy);


            //ToDo: commented just for testing end of game.
            //if (enemy != null)
                //a.a(team + " Queen Fighting with: " + enemy.getClass().getSimpleName());
            //a.a(team + " Queen Fighting with: " + enemy.getClass());

            try {
                Thread.sleep((int) hitSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //a.a("After sleep");
        }

        a.a(team + " Queen died.");

        controller.removeElement(currentImage);
        controller.removeElement(progressBar);

        if (team == Team.A)
            controller.addToBlueCrowns();
        else
            controller.addToRedCrowns();

        a.a(getClass().getSimpleName() + " was removed.");
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
