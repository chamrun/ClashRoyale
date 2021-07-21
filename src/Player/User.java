package Player;

import Accounts.Database;
import Game.Model.Board;
import Game.Model.Level;

public class User extends Player{
    private final Database database;
    private final String name;
    private Level level;
    private int coins;
    private int wins;
    private int loses;

    public String[] getDeck() {
        return deck;
    }

    public User(Database database, String name) {
        this.database = database;
        this.name = name;
        level = Level.ONE;
        wins = 0;
        loses = 0;
        coins = 0;
        deck = new String[]{"Wizard", "BabyDragon", "Archer", "Fireball", "Barbarian", "Valkyrie", "Inferno", "Rage"};
        database.update(name, getDeckString());
    }

    public User(Database database, String name, String deck, int coins, int wins, int loses) {//int level ^ switch level 1->ONE, 2->TWO...
        this.database = database;
        this.name = name;
        this.coins = coins;
        this.wins = wins;
        this.loses = loses;
        this.deck = deck.split(" ");
        level = Level.getLevel(coins);
    }

    private String getDeckString() {
        String deckString = "";
        for (String cardName: deck) {
            deckString += cardName + " ";
        }

        return deckString;
    }



    public void addWin() {
        //System.out.println("Last Coins: " + coins + " - Level: " + level);
        coins += 200;

        wins++;
        if (level.getNextLevelCoins() < coins){
            levelUp();
            //System.out.println("LevelUp to: " + level);
        }
        //System.out.println();

        System.out.println("New Coins: " + coins + " - Level: " + level);
        database.update(name, coins, wins,true);

    }

    public void addLose() {
        //System.out.println("Last Coins: " + coins + " - Level: " + level);
        coins -= 70;
        if (coins < 0){
            coins = 0;
        }

        loses++;
        if (coins < level.getCoins()){
            levelDown();
            //System.out.println("LevelDown to: " + level);
        }
        System.out.println("New Coins: " + coins + " - Level: " + level);
        database.update(name, coins, loses, false);
        //System.out.println();
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public String getUserName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void levelUp() {

        if (level.equals(Level.FOUR))
            level = Level.FIVE;

        if (level.equals(Level.THREE))
            level = Level.FOUR;

        if (level.equals(Level.TWO))
            level = Level.THREE;

        if (level.equals(Level.ONE))
            level = Level.TWO;

        //database.update(name, level.getInt());

    }

    public void levelDown() {

        if (level.equals(Level.TWO))
            level = Level.ONE;

        if (level.equals(Level.THREE))
            level = Level.TWO;

        if (level.equals(Level.FOUR))
            level = Level.THREE;

        if (level.equals(Level.FIVE))
            level = Level.FOUR;

        //database.update(name, level.getInt());

    }

    public double getLevelProgress() {
        return level.getProgress(level, coins);
    }


    public int getCoins() {
        return coins;
    }


    public boolean saveDeck(String[] newDeck){
        if (newDeck.length != 8)
            return false;

        deck = newDeck;
        System.out.println("Going to update deck in db...");
        database.update(name, getDeckString());
        return true;
    }

    @Override
    protected void play() {

    }

    public void setBoard(Board board) {
        //ToDo
    }
}
