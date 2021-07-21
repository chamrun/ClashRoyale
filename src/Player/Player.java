package Player;


import Game.Model.Board;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Level;
import Game.Model.Location;
import Game.Model.Soldiers.*;
import Game.Model.Spells.*;
import Game.Model.Team;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public abstract class Player extends Thread{

    protected Board board;
    protected Level level;
    protected String[] deck;
    protected LinkedList<String> readyCards;
    String nextReadyCard;
    protected Elixir elixir;
    public int getElixir() {
        return elixir.getElixirs();
    }

    /*
    public Player(Board board, Level level){
        this.board = board;
        this.level = level;
    }
    
     */

    @Override
    public void run() {
        elixir = new Elixir();
        elixir.run();
        play();
    }


    protected abstract void play();

    public void setBoard(Board board) {
        this.board = board;
    }

    public void putCard(int index, Location location, Team team) {

        switch (readyCards.get(index)){
            case "Archer"-> new Archers         (board, level, location, team);
            case "Arrows"-> new Arrows          (board, level, location, team);
            case "BabyDragon"-> new BabyDragon  (board, level, location, team);
            case "Barbarian"-> new Barbarian    (board, level, location, team);
            case "Cannon"-> new Cannon          (board, level, location, team);
            case "FireBall"-> new Fireball      (board, level, location, team);
            case "Giant"-> new Giant            (board, level, location, team);
            case "Inferno"-> new InfernoTower   (board, level, location, team);
            case "PEKKA"-> new MiniPEKKA        (board, level, location, team);
            case "Rage"-> new Rage              (board, level, location, team);
            case "Valkyrie"-> new Valkyrie      (board, level, location, team);
            case "Wizard"-> new Wizard          (board, level, location, team);
            default -> {
                System.out.println("WTC?! (What the card)");
                return;
            }
        }

        readyCards.add(nextReadyCard);
        nextReadyCard = getRandomNextCard();
    }



    protected String[] getRandomDeck() {
        String[] allCards = new String[]{"Archer", "Arrows", "BabyDragon", "Barbarian", "Cannon", "FireBall", "Giant", "Inferno", "PEKKA", "Rage", "Valkyrie", "Wizard"};
        String[] randomDeck = new String[8];
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        int i = 0;

        while (i < 4) {
            int randomInt = getRandInt(12);
            if (!usedNumbers.contains(randomInt)){
                randomDeck[i] = allCards[randomInt];
                i++;
                usedNumbers.add(randomInt);
            }
        }

        return randomDeck;
    }


    protected LinkedList<String> getRandomReadyCards() {

        LinkedList<String> randomReadyCards = new LinkedList<>();
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        while (randomReadyCards.size() < 8) {
            int randomInt = getRandInt(8);
            if (!usedNumbers.contains(randomInt)){
                randomReadyCards.add(deck[randomInt]);
                usedNumbers.add(randomInt);
            }
        }

        return randomReadyCards;
    }


    protected String getRandomNextCard() {

        while (true){
            int randomInt = getRandInt(8);
            if (!readyCards.contains(deck[randomInt])){
                nextReadyCard = deck[randomInt];
                break;
            }
        }
        return getRandomNextCard();
    }



    public int getRandInt(int Bound){
        Random random = new Random();
        return random.nextInt(Bound);
    }


}
