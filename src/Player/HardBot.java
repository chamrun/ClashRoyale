package Player;

import Game.Model.Card;
import Game.Model.Location;
import Game.Model.Team;

public class HardBot extends Bot{


    @Override
    protected void play() {
        while (active) {

            if (3 < getElixir()){

                Suggestion suggestion = board.suggestToHardBot();

                if (suggestion == null){
                    putCard(getRandInt(4), new Location(19, 4 + getRandInt(2 * 12)), Team.B);
                    continue;
                }

                String[] suggestedCards = suggestion.getCards();
                Location suggestedLocation = suggestion.getLocation();

                for (String suggestedCard: suggestedCards) {
                    for (int i = 0; i < 4; i++) {
                        if (deck[i].equals(suggestedCard)){
                            if (Card.getCostFromString(deck[i]) <= getElixir()){
                                putCard(i, suggestedLocation, Team.B);
                                break;
                            }
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