package Game.Model;

public class King extends Tower{

    public King(Board board, Level level, Location location, Team team){
        super(board, getHP(level), getDamage(level), 1, 7, location, team);
    }

    @Override
    public void run() {

        while (alive){

            endamage(getNearestEnemy(range));

            try {
                Thread.sleep((int) hitSpeed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 2400;
            case TWO -> hp = 2568;
            case THREE -> hp = 2736;
            case FOUR -> hp = 2904;
            case FIVE -> hp = 3096;

            default -> {
                hp = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return hp;
    }

    private static int getDamage(Level level) {
        int damage;
        switch (level) {
            case ONE -> damage = 50;
            case TWO -> damage = 53;
            case THREE -> damage = 57;
            case FOUR -> damage = 60;
            case FIVE -> damage = 64;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

}
