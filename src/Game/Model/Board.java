package Game.Model;

import java.util.LinkedList;

public class Board {

    LinkedList<Fightable> myArmy;
    LinkedList<Fightable> enemies = null;


    public Fightable getNearestEnemy(Location location) {
        int min = 400;
        Fightable nearestEnemy = null;

        for (Fightable enemy: enemies){
            if (location.getDistance(enemy.getLocation()) < min)
                nearestEnemy = enemy;
        }

        return nearestEnemy;
    }
}
