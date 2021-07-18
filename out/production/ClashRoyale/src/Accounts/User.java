package Accounts;

import Game.Model.Card;
import Game.Model.Level;

public class User {
    private final Database database;
    private final String name;
    private Level level;
    private int coins;
    private int wins;
    private int loses;
    Card[] deck;

    public User(Database database, String name) {
        this.database = database;
        this.name = name;
        level = Level.TWO;
        deck = new Card[8];

        wins = 3;
        loses = 1;
        coins = 350;
    }

    public User(Database database, String name, int coins, int wins, int loses) {//int level ^ switch level 1->ONE, 2->TWO...
        this.database = database;
        this.name = name;
        level = Level.ONE;
        deck = new Card[8];

        //ToDo: Should be read from database
        wins = 1;
        loses = 2;
    }

    public void addWin() {
        coins += 200;
        wins++;
        if (level.getCoins() < coins){
            levelUp();
        }
    }

    public void addLose() {
        coins -= 70;
        if (coins < 0){
            coins = 0;
        }

        loses++;
        if (coins < level.getCoins()){
            levelDown();
        }
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public String getName() {
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

        if (level.equals(Level.FIVE))
            level = Level.FOUR;

        if (level.equals(Level.FOUR))
            level = Level.THREE;

        if (level.equals(Level.THREE))
            level = Level.TWO;

        if (level.equals(Level.TWO))
            level = Level.ONE;

        //database.update(name, level.getInt());

    }

    public double getLevelProgress() {
        return level.getProgress(level, coins);
    }


    public int getCoins() {
        return coins;
    }
}
