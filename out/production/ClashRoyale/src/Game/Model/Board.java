package Game.Model;

import Debugging.a;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Soldiers.*;
import Game.Model.Towers.King;
import Game.Model.Towers.Queen;
import Game.Model.Towers.Tower;
import Player.Suggestion;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board {

    private final int height;
    private final int width;
    private Location[][] locations;
    private LinkedList<Fightable> AFightables;
    private LinkedList<Fightable> BFightables;
    private final ArrayList<Bridge> bridges;
    private final int searchFightableRange;

    public Board( int length, int width, int searchFightableRange) {
        this.height = length;
        this.width = width;
        initializeLocations();
        this.searchFightableRange = searchFightableRange;
        AFightables = new LinkedList<>();
        BFightables = new LinkedList<>();

        //ToDo: setting bridges exactly
        bridges = new ArrayList<>();
        bridges.add(new Bridge(new Location(17, 2), new Location(18, 2)));
        bridges.add(new Bridge(new Location(17, 14), new Location(18, 14)));
    }

    public void initializeLocations(){
        locations = new Location[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0 ; j < height ; j++){
                locations[i][j] =  new Location(i,j);
            }
        }
    }

    public Location[][] getLocations() {
        return locations;
    }

    public ArrayList<Bridge> getBridges() {
        return bridges;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSearchFightableRange() {
        return searchFightableRange;
    }

    public LinkedList<Fightable> getAFightables() {
        return AFightables;
    }

    public LinkedList<Fightable> getBFightables() {
        return BFightables;
    }

        public Fightable getNearestEnemy(Fightable theFightable, double range) {
        double min = range;
        Fightable nearestEnemy = null;
        LinkedList<Fightable> enemy = (theFightable.getTeam().equals(Team.A)) ? BFightables : AFightables;
        for (Fightable fightable : enemy) {
            if (theFightable.getLocation().getRegion().equals(fightable.getLocation().getRegion())) {
                if (theFightable.getLocation().getDistance(fightable.getLocation()) < min) {
                    nearestEnemy = fightable;
                    min = theFightable.getLocation().getDistance(fightable.getLocation());
                }
            }
        }
        return nearestEnemy;
    }

    public void removeFightable(Fightable fightable) {
        //Update screen...

        //TODO: How to to know fightable is in "myArmy" or "enemies"?
//        myArmy.remove(fightable);
//        enemies.remove(fightable);


        if (fightable instanceof Tower) {
            //Update crowns...
            if (fightable instanceof King) {
                gameOver();
            } else {
                //Activates King Tower

            }
        }
    }

    public void removeFightable(Fightable deadFightable, Team killerTeam) {
        //Update screen...

        if (killerTeam.equals(Team.A)){
            BFightables.remove(deadFightable);
        }
        else {
            AFightables.remove(deadFightable);
        }


        if (deadFightable instanceof Tower) {
            //Update crowns...
            if (deadFightable instanceof King) {
                gameOver();
            } else {
                //Activates King Tower
            }
        }


    }

    private void gameOver() {
        //Showing final result and saving game in history
    }

    public Location getNearestTower(Location location, Team team) {
        // TODO: should be this way:
        double minDistance = 20;
        Location nearestTower = null;

        if (team == Team.A){
            for (Fightable bFightable :BFightables){
                if (bFightable instanceof Tower){
                    double distance = location.getDistance(bFightable.getLocation());
                    if (distance < minDistance){
                        minDistance = distance;
                        nearestTower = bFightable.getLocation();
                    }
                }
            }


            if (nearestTower == null) {
                a.a(team + " has no enemyTower?!");
                return new Location(35, 9);
            }

        }
        else if (team == Team.B) {

            for (Fightable aFightable : AFightables) {
                if (aFightable instanceof Tower) {
                    double distance = location.getDistance(aFightable.getLocation());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestTower = aFightable.getLocation();
                    }
                }


                if (nearestTower == null) {
                    a.a(team + " has no enemyTower?!");
                    return new Location(1, 9);
                }
            }
        }

        return nearestTower;

    }


    public boolean isLocationValid(Team team, Location location){
        double y = location.getY();

        if (team == Team.A){
            if (location.getY() < 17){
                return true;
            }

            int nQueens = 0;
            for (Fightable bFightable : BFightables){
                if (bFightable instanceof Queen) {
                    nQueens++;
                }
            }

            if (nQueens == 1 && y < 19){
                return true;
            }

            if (nQueens == 0 && y < 21){
                return true;
            }
        }

        if (team == Team.B){
            if (location.getY() > 16){
                return true;
            }

            int nQueens = 0;
            for (Fightable aFightable : AFightables){
                if (aFightable instanceof Queen) {
                    nQueens++;
                }
            }

            if (nQueens == 1 && y > 18){
                return true;
            }

            if (nQueens == 0 && y > 20){
                return true;
            }
        }

        return false;
    }

    public Location suggestLocationToMediumBot() {
        for (Fightable aFightable : AFightables){
            if ((!(aFightable instanceof Tower)) && aFightable.getLocation().getY() < 9){
                return new Location(20, 4);
            }
        }
        return new Location(20, 16);
    }

    public Suggestion suggestToHardBot() {

        double xMax = 16;
        double y;
        Fightable target = null;

        for (Fightable aFightable: AFightables) {
            if (xMax < aFightable.getLocation().getX()){
                xMax = aFightable.getLocation().getX();
                y = aFightable.getLocation().getY();
                target = aFightable;
            }
        }

        if (target == null){
            return null;
        }

        Location targetLocation = target.getLocation();

        if (target instanceof Barbarian){
            return new Suggestion("BabyDragon","Valkyrie", "Arrows",targetLocation);
        }
        if (target instanceof Archers){
            return new Suggestion("Valkyrie","Barbarian","Arrows",targetLocation);
        }
        if (target instanceof BabyDragon){
            return new Suggestion("Wizard", "BabyDragon","Archer",targetLocation);
        }
        if (target instanceof Wizard){
            return new Suggestion("PEKKA","Barbarian","FireBall",targetLocation);
        }
        if (target instanceof MiniPEKKA){
            return new Suggestion("BabyDragon","FireBall", "Arrows",targetLocation);
        }
        if (target instanceof Giant){
            return new Suggestion("BabyDragon", "Barbarian","PEKKA",targetLocation);
        }
        if (target instanceof Valkyrie){
            return new Suggestion("BabyDragon", "PEKKA" , "FireBall", targetLocation);
        }
        if (target instanceof Cannon){
            return new Suggestion("BabyDragon","Giant","Valkyrie",targetLocation);
        }
        if (target instanceof InfernoTower){
            return new Suggestion("Barbarian","PEKKA","Giant",targetLocation);
        }

        return null;
    }


}
