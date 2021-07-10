package Game.Model;

import java.util.LinkedList;

public class Board {

    LinkedList<Fightable> myArmy;
    LinkedList<Fightable> enemies = null;


    public Fightable getNearestEnemy(Location location, double range) {
        double min = range;
        Fightable nearestEnemy = null;

        for (Fightable enemy: enemies){
            if (location.getDistance(enemy.getLocation()) < min) {
                nearestEnemy = enemy;
                min = location.getDistance(enemy.getLocation());
            }
        }

        return nearestEnemy;
    }

    public void removeFightable(Fightable fightable) {
        //Update screen...

        //TODO: How to to know fightable is in "myArmy" or "enemies"?
        myArmy.remove(fightable);
        enemies.remove(fightable);


        if (fightable instanceof Tower){
            //Update crowns...
            if (fightable instanceof King){
                gameOver();
            }
            else {
                //Activates King Tower

            }
        }
    }

    private void gameOver() {
        //Showing final result and saving game in history
    }
}
