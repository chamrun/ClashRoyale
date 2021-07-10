package Game.Model;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        if (18 < x || 36 < y || x < 0 || y < 0){
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance(Location src){
        return ((x - src.getX()) * (x - src.getX()) + (y - src.getY()) * (y - src.getY()));
    }
}
