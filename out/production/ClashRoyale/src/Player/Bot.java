package Player;

import java.util.Arrays;

public abstract class Bot extends Player {
    protected boolean active;

    public Bot(){
        System.out.println("Making a new bot...");
        active = true;
        deck = getRandomDeck();
        System.out.println("Bot Deck is ready...");
        readyCards = getRandomReadyCards();
        System.out.println("Bot ReadyCards are ready...");
        nextReadyCard = getRandomNextCard();


        System.out.println("readyCards: " + readyCards);
        System.out.println("deck: " + Arrays.toString(deck));
        System.out.println("nextReadyCard: " + nextReadyCard);
        System.out.println("Bot was made!\n");

        start();

    }


    public void inactive(){
        active = false;
    }





}
