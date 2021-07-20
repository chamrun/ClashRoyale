package Game.Model;

public class Location {
    private int x;
    private int y;
    private boolean isEmpty;

    public boolean isEmpty() {
        return isEmpty;
    }

    public Location(int x, int y) {
        if (18 < x || 36 < y || x < 0 || y < 0) {
            System.out.println("(" + x + "," + y + ") is not valid.");
            if (18 < x)
                x = 17;
            if (18 < y)
                y = 17;
            if (x < 0)
                x = 0;
            if (y < 0)
                y = 0;

        }

        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Region getRegion() {
        if (x < 16)
        return Region.A;
        else return Region.B;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistance(Location src) {
        return Math.sqrt(Math.pow(x - src.getX(), 2) + Math.pow(y - src.getY(), 2));
    }
}
