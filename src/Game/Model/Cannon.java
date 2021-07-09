package Game.Model;

public class Cannon extends Building{

    public Cannon(Level level){
        int hp, damage;

        switch (level){
            case ONE -> {
                hp = 380;
                damage = 60;
            }
            case TWO -> {
                hp = 418;
                damage = 66;
            }
            case THREE -> {
                hp = 459;
                damage = 72;
            }
            case FOUR -> {
                hp = 505;
                damage = 70;
            }
            case FIVE -> {
                hp = 554;
                damage = 87;
            }
            default -> {
                hp = 0;
                damage = 0;
                System.out.println(level + " is an invalid level :/");
            }
        }

        super(hp, damage, 0.8, 5.5, Target.GROUND, 30, 6);
    }

}
