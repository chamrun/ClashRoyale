package Player;

import Debugging.a;
import Game.Model.Location;
import Game.Model.Team;

public class EasyBot extends Bot{

    @Override
    protected void play() {
        a.a("EasyBot starting to play...");

        while (active) {

            if (5 < getElixir()){
                Location randomLocation = new Location(18 + getRandInt(16), 2 + getRandInt(12));
                putCard(getRandInt(4), randomLocation, Team.B);
                //putCard(getRandInt(4), new Location(22, 14), Team.B);
            }
            else {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
