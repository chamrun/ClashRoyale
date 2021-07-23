package Game.Model.Towers;

import Game.Controller.GameController;
import Game.Model.Board;
import Game.Model.Level;
import Game.Model.Location;
import Game.Model.Team;

public class King extends Tower {

    public King(Board board, Level level, Location location, Team team , GameController controller){
        super(board, getHP(level), getDamage(level), 1000, 7, location, team,controller);
        //ToDo
        //super(board, 300, getDamage(level), 1000, 7, location, team,controller);

        currentImage.setFitHeight(144);
        currentImage.setFitWidth(55.5);

        start();
    }

    @Override
    public void run() {

        while (alive){

            //endamage(getNearestEnemy(range));
            //ToDo: commented just for testing end of game.

            try {
                Thread.sleep(hitSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        controller.removeElement(currentImage);
        controller.removeElement(progressBar);

        controller.endGameOutside(team);
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 2400;
            case TWO -> hp = 2568;
            case THREE -> hp = 2736;
            case FOUR -> hp = 2904;
            case FIVE -> hp = 3096;

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
            case TWO -> damage = 53;
            case THREE -> damage = 57;
            case FOUR -> damage = 60;
            case FIVE -> damage = 64;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
