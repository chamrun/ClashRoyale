package Game.Model;

public interface Card {
    int getCost();

    static int getCostFromString(String cardName){
        int cost = 11;

        switch (cardName){
            case "Archer", "Arrows", "BabyDragon", "Cannon", "Rage" -> cost = 3;
            case "Barbarian", "Inferno", "Giant", "Wizard" -> cost = 5;
            case "FireBall", "PEKKA", "Valkyrie" -> cost = 4;
            default -> {
                System.out.println("WTC?! (What the card): " + cardName);
                return 11;
            }
        }

        return cost;

    }

}
