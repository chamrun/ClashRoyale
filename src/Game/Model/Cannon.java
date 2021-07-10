package Game.Model;

public class Cannon extends Building implements Card, Runnable{

    public Cannon(Level level, Location location){

        super(getHP(level), getDamage(level), 0.8, 5.5, Target.GROUND, 30, 6, location);

        run();

    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 380;
            case TWO -> hp = 418;
            case THREE -> hp = 459;
            case FOUR -> hp = 505;
            case FIVE -> hp = 554;

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
            case ONE -> damage = 60;
            case TWO -> damage = 66;
            case THREE -> damage = 72;
            case FOUR -> damage = 70;
            case FIVE -> damage = 87;

            default -> {
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        return damage;
    }

    @Override
    public int getCost() {
        return super.cost;
    }


    @Override
    public void run() {

        while (isAlive){
            long start = System.currentTimeMillis();

            endamage();

            try {
                Thread.sleep((int) hitSpeed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (start + super.lifeTime < System.currentTimeMillis()){
                return;
            }
        }
    }


    public void endamage() {
        Fightable enemy = Board.getNearestEnemy(location);
        super.endamage(enemy);
    }
}
