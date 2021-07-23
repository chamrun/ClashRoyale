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
                Location randomLocation = new Location(19 + getRandInt(16), getRandInt(19));
                putCard(getRandInt(4), randomLocation, Team.B);
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
