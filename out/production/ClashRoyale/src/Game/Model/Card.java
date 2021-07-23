package Game.Model;

public interface Card {
    int getCost();

    static int getCostFromString(String cardName){
        int cost;

        switch (cardName){
            case "Archers", "Arrows", "BabyDragon", "Cannon", "Rage" -> cost = 3;
            case "Barbarian", "InfernoTower", "Giant", "Wizard" -> cost = 5;
            case "Fireball", "MiniPEKKA", "Valkyrie" -> cost = 4;
            default -> {
                System.out.println("WTC?! (What the card): " + cardName);
                return 11;
            }
        }

        return cost;

    }

}
