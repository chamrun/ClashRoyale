package Player;


import Debugging.a;
import Game.Controller.GameController;
import Game.Model.*;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Soldiers.*;
import Game.Model.Spells.Arrows;
import Game.Model.Spells.Fireball;
import Game.Model.Spells.Rage;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public abstract class Player extends Thread{

    protected GameController gameController;

    public void setter(GameController gameController, Board board, Level level) {
        this.gameController = gameController;
        this.board = board;
        this.level = level;

        start();
    }

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
        a.a(getClass().getSimpleName() + " started running...");
        elixir = new Elixir();
        a.a(getClass().getSimpleName() + " elixir set...");
        play();
    }


    protected abstract void play();


    public void putCard(int index, Location location, Team team) {

        a.a(this.getClass().getSimpleName() + " want to put " + index + "th card on " + location);

        Card newCard;

        if (getElixir() < Card.getCostFromString(deck[index]))
            return;


        switch (readyCards.get(index)){
            case "Archers"->    newCard = new Archers       (board, level, location, team, gameController);
            case "BabyDragon"-> newCard = new BabyDragon    (board, level, location, team, gameController);
            case "Barbarian"->  newCard = new Barbarian     (board, level, location, team, gameController);
            case "Cannon"->     newCard = new Cannon        (board, level, location, team, gameController);
            case "Giant"->      newCard = new Giant         (board, level, location, team, gameController);
            case "InfernoTower"->newCard= new InfernoTower  (board, level, location, team, gameController);
            case "MiniPEKKA"->  newCard = new MiniPEKKA     (board, level, location, team, gameController);
            case "Valkyrie"->   newCard = new Valkyrie      (board, level, location, team, gameController);
            case "Wizard"->     newCard = new Wizard        (board, level, location, team, gameController);
            case "Rage"->       newCard = new Rage          (board, level, location, team);
            case "Arrows"->     newCard = new Arrows        (board, level, location, team);
            case "Fireball"->   newCard = new Fireball      (board, level, location, team);
            default -> {
                System.out.println("WTC?! (What the card): " + readyCards.get(index));
                return;
            }
        }

        a.a(index + "th card: " + newCard.getClass().getSimpleName());

        if (!elixir.use(newCard.getCost())){
            System.out.println("-ERR- Not enough elixir for " + readyCards.get(index) + " (" + getElixir()+ ")");
            return;
        }

        if (newCard instanceof Fightable)
            Platform.runLater(() -> gameController.landPane.getChildren().add(((Fightable) newCard).getCurrentImage()));


        readyCards.remove(index);
        readyCards.add(nextReadyCard);
        nextReadyCard = getRandomNextCard();
        if (newCard instanceof Fightable)
            board.addBFightable((Fightable) newCard);

        //ToDo: should be added to board LinkedList?
    }



    protected static String[] getRandomDeck() {
        String[] allCards = new String[]{"Archers", "BabyDragon", "Barbarian", "Cannon", "Fireball", "Giant", "InfernoTower", "MiniPEKKA", "Valkyrie", "Wizard"};
        String[] randomDeck = new String[8];
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        int i = 0;

        while (i < 8) {
            int randomInt = getRandInt(10);
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

        while (randomReadyCards.size() < 4) {
            int randomInt = getRandInt(8);
            if (!usedNumbers.contains(randomInt)){
                randomReadyCards.add(deck[randomInt]);
                usedNumbers.add(randomInt);
            }
        }

        return randomReadyCards;
    }


    protected String getRandomNextCard() {


        int i = 0;
        while (true){
            int randomInt = getRandInt(8);
            if (!readyCards.contains(deck[randomInt])){
                nextReadyCard = deck[randomInt];
                break;
            }
            i++;
            if (20 < i)
                break;
        }



        return nextReadyCard;
    }



    public static int getRandInt(int Bound){
        Random random = new Random();
        return random.nextInt(Bound);
    }


}
