package Player;

import Game.Model.Board;

import java.util.Random;

public class MediumBot extends Bot{

    public MediumBot(Board board) {
        super(board);
    }

    @Override
    protected void play() {
        Random random = new Random();
        int randomInt;

        while (true) {
            randomInt = random.nextInt(3) + 3;

            if (randomInt < getElixir()){

            }


        }
    }

}
