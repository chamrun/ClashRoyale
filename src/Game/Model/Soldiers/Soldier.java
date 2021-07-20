package Game.Model.Soldiers;

import Debugging.Deb;
import Game.Model.*;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Soldier extends Fightable implements Card {


    protected Speed speed;
    protected final Target target;
    protected final boolean isAreaSplash;
    protected final int count;
    protected final int cost;

    protected Mode mode;


    protected long moveTime;
    protected int moveProgress;
    protected ImageView walk_closed_r;
    protected ImageView walk_open_r;
    protected ImageView walk_closed_l;
    protected ImageView walk_open_l;
    protected ImageView walk_closed_u;
    protected ImageView walk_open_u;
    protected ImageView walk_closed_d;
    protected ImageView walk_open_d;


    protected long fightTime;
    protected ImageView fight_1_r;
    protected ImageView fight_1_l;
    protected ImageView fight_1_u;
    protected ImageView fight_1_p;
    protected ImageView fight_2_r;
    protected ImageView fight_2_l;
    protected ImageView fight_2_u;
    protected ImageView fight_2_p;
    protected ImageView fight_3_r;
    protected ImageView fight_3_l;
    protected ImageView fight_3_u;
    protected ImageView fight_3_p;
    protected ImageView fight_4_r;
    protected ImageView fight_4_l;
    protected ImageView fight_4_u;
    protected ImageView fight_4_p;
    protected ImageView[] fightImageViews;


    public Soldier(Board board, int hp, int damage, long hitSpeed, double range, Location location, Speed speed, Target target,
                   boolean isAreaSplash, int count, int cost, Team team, Type type) {
        super(board, hp, damage, hitSpeed, range, location, team, type);
        this.speed = speed;
        this.target = target;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.cost = cost;
        fightTime = (long) hitSpeed * 1000;
        moveTime = getMoveTime();

        initializeWalkImages();
        initializeFightImages();

    }

    public void initializeFightImages() {
        String address = null;
        fightImageViews[0] = fight_1_r;
        fightImageViews[4] = fight_1_l;
        fightImageViews[8] = fight_1_u;
        fightImageViews[12] = fight_1_p;
        fightImageViews[1] = fight_2_r;
        fightImageViews[5] = fight_2_l;
        fightImageViews[9] = fight_2_u;
        fightImageViews[13] = fight_2_p;
        fightImageViews[2] = fight_3_r;
        fightImageViews[6] = fight_3_l;
        fightImageViews[10] = fight_3_u;
        fightImageViews[14] = fight_3_p;
        fightImageViews[3] = fight_4_r;
        fightImageViews[7] = fight_4_l;
        fightImageViews[11] = fight_4_u;
        fightImageViews[15] = fight_4_p;

        for (int i = 1; i < 5; i++) {
            if (this instanceof Barbarian) {
                address = "characters/Barbarians/Bar_Fight_" + i + ".png";
            } else if (this instanceof Archers) {
                address = "characters/Archers/Arc_Fight_" + i + ".png";
            } else if (this instanceof BabyDragon) {
                address = "characters/BabyDragon/Bab_Fight_" + i + ".png";
            } else if (this instanceof Giant) {
                address = "characters/Giant/Gia_Fight_" + i + ".png";
            } else if (this instanceof MiniPEKKA) {
                address = "characters/MiniPEKKA/Min_Fight_" + i + ".png";
            } else if (this instanceof Valkyrie) {
                address = "characters/MiniPEKKA/Min_Fight_" + i + ".png";
            } else if (this instanceof Wizard) {
                address = "characters/Wizard/Wiz_Fight_" + i + ".png";
            }
            makeRotationForms(fightImageViews[i - 1], fightImageViews[i + 3],
                    fightImageViews[i + 7], fightImageViews[i + 11], address);
        }
    }

    public void initializeWalkImages() {
        String addressForOpen = null;
        String addressForClosed = null;

        if (this instanceof Barbarian) {
            addressForClosed = "characters/Barbarians/Bar_Walk_Closed.png";
            addressForOpen = "characters/Barbarians/Bar_Walk_Open.png";
        } else if (this instanceof Archers) {
            addressForClosed = "characters/Archers/Arc_Walk_Closed.png";
            addressForOpen = "characters/Archers/Arc_Walk_Open.png";
        } else if (this instanceof BabyDragon) {
            addressForClosed = "characters/BabyDragon/Bab_Walk_Open.png";
            addressForOpen = addressForClosed;
        } else if (this instanceof Giant) {
            addressForClosed = "characters/Giant/Gia_Walk_Closed.png";
            addressForOpen = "characters/Giant/Gia_Walk_Open.png";
        } else if (this instanceof MiniPEKKA) {
            addressForClosed = "characters/MiniPEKKA/Min_Walk_Closed.png";
            addressForOpen = "characters/MiniPEKKA/Min_Walk_Open.png";
        } else if (this instanceof Valkyrie) {
            addressForClosed = "characters/MiniPEKKA/Min_Walk_Closed.png";
            addressForOpen = "characters/MiniPEKKA/Min_Walk_Open.png";
        } else if (this instanceof Wizard) {
            addressForClosed = "characters/Wizard/Wiz_Walk_Closed.png";
            addressForOpen = "characters/Wizard/Wiz_Walk_Open.png";
        }
        makeRotationForms(walk_open_r, walk_open_l, walk_open_u, walk_open_d, addressForOpen);
        makeRotationForms(walk_closed_r, walk_closed_l, walk_closed_u, walk_closed_d, addressForClosed);

    }

    public void makeRotationForms(ImageView imageView1, ImageView imageView2, ImageView imageView3,
                                  ImageView imageView4, String address) {
        imageView1 = new ImageView(new Image(address));
        for (int i = 0; i < 3; i++) {
            ImageView base = new ImageView(new Image(address));
            if (i == 0) {
                base.setRotationAxis(new Point3D(0, 1, 0));
                base.setRotate(-180);
                imageView2 = base;
            } else {
                base.setRotationAxis(new Point3D(0, 0, 1));
                if (i == 1) {
                    base.setRotate(-90);
                    imageView3 = base;
                } else {
                    base.setRotate(90);
                    imageView4 = base;
                }
            }
        }
    }

    public Mode getMode() {
        return mode;
    }

    public int getMoveProgress() {
        return moveProgress;
    }

    @Override
    public void run() {
        while (alive) {
            LinkedList<Fightable> enemies = getNearEnemies();

            if (enemies == null) {
                move();
                try {
                    Thread.sleep(moveTime);
                    Deb.print(toString() + "between two damages : " + moveTime + "seconds.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                fight(enemies);
                try {
                    Thread.sleep(hitSpeed);
                    Deb.print(toString() + "between two damages : " + hitSpeed + "seconds.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //live();
    //die();

}

    public void move() {

        //find nearest enemy
        //if it is null go to bridge
        //if it is on bridge go over

        Fightable target = getNearestEnemy(board.getSearchFightableRange());
        if (target != null) {
            move(target.getLocation());
            Deb.print(toString() + " moving towards the target : " + target.toString() + " in X = " + target.getLocation().getX()
                    + " Y = " + target.getLocation().getY());
        } else {
            move(getNearestBridge());
            Deb.print(toString() + " moving towards the bridge head : " + " in X = " + getLocation().getX()
                    + " Y = " + target.getLocation().getY());
        }
    }

    /*
    public void live() {


        TimerTask move = new TimerTask() {
            @Override
            public void run() {

                ArrayList<Fightable> targets = new ArrayList<>();
                targets.add(getNearestEnemy(board.getSearchFightableRange()));

                if (!alive)
                    return;

                if (targets == null) {
                    Location dest = getNearestBridge();
                    move(dest);
                }

                else {
                    if (location.getDistance(targets.get(0).getLocation()) <= range) {
                        fight(targets);
                    } else {
                        Location dest = targets.get(0).getLocation();
                        move(dest);
                    }
                }
            }
        };
        moveTimer.schedule(move,0,moveTime);
    }
     */


    public LinkedList<Fightable> getNearEnemies() {
        LinkedList<Fightable> nearEnemies = new LinkedList<>();
        LinkedList<Fightable> enemies = (this.team.equals(Team.A)) ? board.getBFightables() : board.getAFightables();

        if (isAreaSplash) {

            for (Fightable enemy : enemies) {
                if (location.getDistance(enemy.getLocation()) <= range) {
                    if (isValidEnemy(enemy)) {
                        nearEnemies.add(enemy);
                    }
                }
            }

            if (nearEnemies.size() == 0) {
                return null;
            }
        } else {// AreaSplash == false -> we'll return the nearest enemy
            Fightable nearestEnemy = getNearestEnemy(enemies);

            if (nearestEnemy == null) {
                return null;
            }

            nearEnemies.add(nearestEnemy);
        }

        return nearEnemies;
    }


    public Fightable getNearestEnemy(LinkedList<Fightable> enemies) {
        double min = range;
        Fightable nearestEnemy = null;

        for (Fightable enemy : enemies) {
            if (location.getDistance(enemy.getLocation()) <= min) {
                if (isValidEnemy(enemy)) {
                    nearestEnemy = enemy;
                    min = location.getDistance(enemy.getLocation());
                }
            }
        }
        return nearestEnemy;
    }

    public void changeSpeed() {
        //todo : relationship between speed and numbers
    }


    public void fight(LinkedList<Fightable> targets) {

        while (alive) {
            for (Fightable target : targets) {
                target.toGetHurt(damage);
            }
        }
    }



    public void move(Location destination) {
//        mode = Mode.MOVE;
        if (destination.getX() == location.getX()) {
            if (destination.getY() > location.getY())
                location.setY(location.getY() + 1);
            else
                location.setY(location.getY() - 1);
        } else {
            if (destination.getX() > location.getX())
                location.setX(location.getX() + 1);
            else
                location.setX(location.getX() - 1);
        }
    }

    public Location getNearestBridge() {
        //TODO: should be transferred to "Board", and bridges needs some changes.
        double min = board.getLength();
        Location nearestBridge = null;

        if (isOnBridge()) {
            return getAnotherHead();
        }

        if (location.getRegion().equals(Region.A)) {
            for (Bridge bridge : board.getBridges()) {
                if (bridge.getAHead().getDistance(location) < min) {
                    min = bridge.getAHead().getDistance(location);
                    nearestBridge = bridge.getAHead();
                }
            }
        } else {
            for (Bridge bridge : board.getBridges()) {
                if (bridge.getBHead().getDistance(location) < min) {
                    min = bridge.getBHead().getDistance(location);
                    nearestBridge = bridge.getBHead();
                }
            }
        }
        return nearestBridge;
    }

    public Location getAnotherHead() {
        if (location.getY() == board.getBridges().get(0).getAHead().getY() )
            return (team.equals(Team.A))?board.getBridges().get(0).getBHead():board.getBridges().get(0).getAHead();
        else
            return (team.equals(Team.A))?board.getBridges().get(1).getBHead():board.getBridges().get(1).getAHead();
    }

    public boolean isOnBridge() {
        if (location.getY() == board.getBridges().get(0).getAHead().getY() ){
            if (location.getX() >= board.getBridges().get(0).getAHead().getX()
            && location.getX() <= board.getBridges().get(0).getBHead().getY())
                return true;
        }else if (location.getY() == board.getBridges().get(1).getAHead().getY() ){
            if (location.getX() >= board.getBridges().get(1).getAHead().getX()
                    && location.getX() <= board.getBridges().get(1).getBHead().getY())
                return true;
        }
        return false;
    }

    public long getMoveTime() {
        // TODO
        if (speed.equals(Speed.SLOW))
            return 3 * 1000;
        else if (speed.equals(Speed.MEDIUM))
            return 2 * 1000;
        else
            return 1 * 1000;
    }

    public void die() {

        //This method is to short, we can put it in the end of "run method".
        board.removeFightable(this, team);

        /*
        if (team.equals(Team.A))
            board.getAFightables().remove(this);
        else
            board.getBFightables().remove(this);

         */
    }

    public boolean isValidEnemy(Fightable enemy) {

        Type enemyType = enemy.getType();

        if (target.equals(Target.GROUND) && enemyType.equals(Type.AIR))
            return false;

        if (target.equals(Target.BUILDINGS) && !enemy.getType().equals(Type.BUILDING))
            return false;

        return true;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void rage(long duration) {
        damage *= 1.4;
        hitSpeed /= 1.4;
        // TODO speed?

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                damage /= 1.4;
                hitSpeed *= 1.4;
            }
        }, duration);
    }
}
