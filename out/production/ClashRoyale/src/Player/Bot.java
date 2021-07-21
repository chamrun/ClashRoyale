package Player;

import Game.Model.Board;

public abstract class Bot extends Player {
    protected boolean active;

    public Bot(Board board){
        active = true;
        this.board = board;
        deck = getRandomDeck();
        readyCards = getRandomReadyCards();
        nextReadyCard = getRandomNextCard();
    }


    public void inactive(){
        active = false;
    }





}
