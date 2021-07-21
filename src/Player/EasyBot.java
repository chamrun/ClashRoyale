package Player;

import Game.Model.Board;
import Game.Model.Location;
import Game.Model.Team;

public class EasyBot extends Bot{


    public EasyBot(Board board) {
        super(board);
    }

    @Override
    protected void play() {
        while (true) {

            if (5 < getElixir()){
                Location randomLocation = new Location(getRandInt(18), 19 + getRandInt(16));
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
