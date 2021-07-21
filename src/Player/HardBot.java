package Player;

import Game.Model.Board;
import Game.Model.Location;
import Game.Model.Team;

public class HardBot extends Bot{

    public HardBot(Board board) {
        super(board);
    }

    @Override
    protected void play() {
        while (true) {

            if (3 < getElixir()){

                Suggestion suggestion = board.suggestToHardBot();
                String[] suggestedCards = suggestion.getCards();
                Location suggestedLocation = suggestion.getLocation();
                for (String suggestedCard: suggestedCards) {
                    for (int i = 0; i < 4; i++) {
                        if (deck[i].equals(suggestedCard)){
                            putCard(i, suggestedLocation, Team.B);
                        }
                    }
                }
                if (5 < getElixir()) {
                    putCard(getRandInt(4), suggestedLocation, Team.B);
                }
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