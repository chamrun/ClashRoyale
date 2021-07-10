package Game.Model;

public class InfernoTower extends Building{
    int maxDamage;

    public InfernoTower(Board board, Level level, Location location){

        super(board, getHP(level), getDamage(level), 0.4, 6, Target.GROUND_AIR, 40, 5, location);

        switch (level) {
            case ONE -> maxDamage = 400;
            case TWO -> maxDamage = 440;
            case THREE -> maxDamage = 484;
            case FOUR -> maxDamage = 532;
            case FIVE -> maxDamage = 584;

            default -> {
                maxDamage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        run();

    }

    private static int getHP(Level level) {
        int hp;

        switch (level) {
            case ONE -> hp = 800;
            case TWO -> hp = 880;
            case THREE -> hp = 968;
            case FOUR -> hp = 1064;
            case FIVE -> hp = 1168;

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
            case ONE -> damage = 20;
            case TWO -> damage = 22;
            case THREE -> damage = 24;
            case FOUR -> damage = 26;
            case FIVE -> damage = 29;

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

        int damageChanges = (maxDamage - damage) / 100;

        while (isAlive){
            long start = System.currentTimeMillis();

            Fightable target = board.getNearestEnemy(location);
            endamage(target);

            try {
                Thread.sleep((int) hitSpeed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (start + super.lifeTime < System.currentTimeMillis()){
                return;
            }

            damage += damageChanges;
        }
    }
}
