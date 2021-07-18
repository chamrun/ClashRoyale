package Game.Model;

public class Bridge {
    private final Location AHead;
    private final Location BHead;

    public Bridge(Location AHead, Location BHead) {
        this.AHead = AHead;
        this.BHead = BHead;
    }

    public Location getAHead() {
        return AHead;
    }

    public Location getBHead() {
        return BHead;
    }
}
