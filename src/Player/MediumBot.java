package Player;

import Game.Model.Board;
import Game.Model.Location;
import Game.Model.Team;

public class MediumBot extends Bot{

    public MediumBot(Board board) {
        super(board);
    }

    @Override
    protected void play() {
        while (true) {

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