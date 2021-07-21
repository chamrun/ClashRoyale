package Player;

import Game.Model.Location;

public class Suggestion {
    String[] cards;
    Location location;

    public Suggestion(String firstCard, String secondCard, String thirdCard, Location location) {
        this.cards = new String[]{firstCard, secondCard, thirdCard};
        this.location = location;
    }

    public String[] getCards() {
        return cards;
    }

    public Location getLocation() {
        return location;
    }
}
