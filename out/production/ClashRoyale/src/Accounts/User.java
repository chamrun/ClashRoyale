package Accounts;

import Game.Model.Card;
import Game.Model.Level;

public class User{
    private final Database database;
    private final String name;
    private Level level;
    Card[] cards;

    public User(Database database, String name) {//int level ^ switch level 1->ONE, 2->TWO...
        this.database = database;
        this.name = name;
        level = Level.ONE;
        cards = new Card[8];
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void levelUp(){

        if (level.equals(Level.FOUR))
            level = Level.FIVE;

        if (level.equals(Level.THREE))
            level = Level.FOUR;

        if (level.equals(Level.TWO))
            level = Level.THREE;

        if (level.equals(Level.ONE))
            level = Level.TWO;

        database.update(name, level.getInt());

    }
}
