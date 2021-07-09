package Game.Model;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        if (18 < x || 36 < y || x < 0 || y < 0){
            System.out.println("(" + x + "," + y + ") is not valid.");
            x = 17;
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
}
