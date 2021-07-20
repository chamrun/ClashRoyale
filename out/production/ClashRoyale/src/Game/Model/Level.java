package Game.Model;

public enum Level {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public int getInt(){
        switch (this){

            case ONE -> {
                return 1;
            }

            case TWO -> {
                return 2;
            }

            case THREE -> {
                return 3;
            }
            case FOUR -> {
                return 4;
            }

            case FIVE -> {
                return 5;
            }

        }
        return 1;
    }

    public double getProgress(Level level, int coins) {
        double progress = ((double) coins - level.getCoins()) / (level.getNextLevelCoins() - level.getCoins());
        System.out.println(coins + "-" + level.getCoins() + "/" + level.getNextLevelCoins() + "-" + level.getCoins());
        System.out.println(" = progress = " + progress);
        return progress;
    }

    public int getCoins() {

        switch (this){

            case  ONE -> {
                return 0;
            }

            case TWO -> {
                return 300;
            }

            case THREE -> {
                return 500;
            }

            case FOUR -> {
                return 900;
            }
            case FIVE -> {
                return 1700;
            }
        }

        System.out.println("Error in LEVEL!");
        return 2;
    }

    public static Level getLevel(int coins) {
        if (coins < 300)
            return ONE;

        if (coins < 500)
            return TWO;

        if (coins < 900)
            return THREE;

        if (coins < 1700)
            return FOUR;

        return FIVE;
    }

    public int getNextLevelCoins() {
        switch (this){

            case ONE -> {
                return 300;
            }

            case TWO -> {
                return 500;
            }

            case THREE -> {
                return 900;
            }
            case FOUR -> {
                return 1700;
            }

            case FIVE -> {
                return 2500;
            }

        }
        System.out.println("Error in LEVEL!");
        return 1;
    }
}
