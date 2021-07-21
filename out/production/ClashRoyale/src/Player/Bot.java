package Player;

import Game.Model.Board;

public abstract class Bot extends Player {

    public Bot(Board board){
        this.board = board;
        deck = getRandomDeck();
        readyCards = getRandomReadyCards();
        nextReadyCard = getRandomNextCard();
    }





}
