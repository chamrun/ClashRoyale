package Game.Model;

import Game.Model.Towers.King;
import Game.Model.Towers.Tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Board {

    private final int length;
    private final int width;
    private Location[][] locations;
    private HashMap<Team, Region> teams;
    private LinkedList<Fightable> AFightables;
    private LinkedList<Fightable> BFightables;
    private final ArrayList<Bridge> bridges;
    private final int searchFightableRange;



    public Board(HashMap<Team, Region> teams, int length, int width, ArrayList<Bridge> bridges, int searchFightableRange) {
        this.length = length;
        this.width = width;
        initializeLocations();
        this.bridges = bridges;
        this.searchFightableRange = searchFightableRange;
        this.teams.put(Team.A, Region.A);
        this.teams.put(Team.B, Region.B);
        AFightables = new LinkedList<>();
        BFightables = new LinkedList<>();
    }

    public void initializeLocations(){
        for (int i = 0; i < length ; i++){
            for (int j = 0 ; j < width ; j++){
                locations[i][j] = new Location(i,j);
            }
        }
    }

    public Location[][] getLocations() {
        return locations;
    }

    public ArrayList<Bridge> getBridges() {
        return bridges;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getSearchFightableRange() {
        return searchFightableRange;
    }

    public HashMap<Team, Region> getTeams() {
        return teams;
    }

    public LinkedList<Fightable> getAFightables() {
        return AFightables;
    }

    public LinkedList<Fightable> getBFightables() {
        return BFightables;
    }

    //    public Fightable getNearestEnemy(Fightable theFightable, double range) {
//        double min = range;
//        Fightable nearestEnemy = null;
//        LinkedList<Fightable> enemy = (theFightable.getTeam().equals(Team.A)) ? BFightables : AFightables;
//        for (Fightable fightable : enemy) {
//            if (theFightable.getLocation().getRegion().equals(fightable.getLocation().getRegion())) {
//                if (theFightable.getLocation().getDistance(fightable.getLocation()) < min) {
//                    nearestEnemy = fightable;
//                    min = theFightable.getLocation().getDistance(fightable.getLocation());
//                }
//            }
//        }
//        return nearestEnemy;
//    }

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

    public Location getNearestTower(Location location) {
        // TODO: should be this way:
        /*
        if (width / 2 < location.getX())
            return bridges.getAHead();
        else
            return bridges.getBHead();

         */

        return null;
    }
}
