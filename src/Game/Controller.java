package Game;

import Game.Model.Card;
import javafx.event.Event;
import javafx.fxml.FXML;

public class Controller {

    Card selectedCard = null;

    @FXML
    public void insertCard(Event event){

        if (selectedCard == null){
            showError("No card selected!");
        }

        // if (selected location is not valid)
            showError("Invalid location!");

        // Adding a new card to deck
        // Showing a new random card, as next card
    }

    private void showError(String errorDescription) {
        System.out.println(errorDescription);
        // printing error on screen, for a few seconds.

    }

    @FXML
    public void selectCard (Event event){
        //selectedCard = new event.getTarget();
        //Card card = Card.getCard(event.getTarget()); ??
    }

    private Card getRandomCard(){
        return null;
        //return Card.getNewRandomCard(); ??
    }


}
