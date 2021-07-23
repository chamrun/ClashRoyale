package Player;

import Game.Model.Location;
import Game.Model.Team;

public class MediumBot extends Bot{

    @Override
    protected void play() {
        while (active) {

            if (3 + getRandInt(3) < getElixir()){
                Location busyLocation = board.suggestLocationToMediumBot();
                putCard(getRandInt(4), busyLocation, Team.B);
            }
            else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}