package Player;

import Game.Model.Board;

public abstract class Bot extends Player {
    Board board;
    public Bot(Board board){
        this.board = board;
    }

}
