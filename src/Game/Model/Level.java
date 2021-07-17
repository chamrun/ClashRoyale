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
}
