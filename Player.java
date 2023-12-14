import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private String playerName;
    private int hp;
    private int energyPoints;
    private Map<String, Integer> powerUpEnergyPoints;
    private List<String> powerUps;
    public static final int MAX_INVENTORY_SIZE = 8;
    private List<String> inventory;
    private Map<String, PowerUps> powerUpsInventory = new HashMap<>();




    public Player(String playerName, int initialHp) {
        this.playerName = playerName;
        this.hp = initialHp;
        this.energyPoints = 0;
        this.powerUpEnergyPoints = new HashMap<>();
        this.powerUps = new ArrayList<>();
        this.inventory = new ArrayList<>();
        initializePowerUps();
        System.out.println("\nYour name is: " + playerName + ". Your HP is at " + initialHp);
    }

    public List<String> getPowerUps() {
        return powerUps;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public List<String> getInventory() {
        return new ArrayList<>(inventory);
    }

    public void receiveAttack(int damage) {
        // Ensure that the player's health points don't go below zero
        hp = Math.max(0, hp - damage);

        System.out.println(playerName + " received " + damage + " damage. Current HP: " + hp);
    }

    
    public void restoreHp(int amount) {
        hp += amount;
        System.out.println(playerName + " restored " + amount + " HP. Current HP: " + hp);
    }

    private void initializePowerUps() {
        powerUpEnergyPoints.put("Water Bottle", 10);
        powerUpEnergyPoints.put("Protein Bar", 15);
        powerUpEnergyPoints.put("Lip Balm", 5);
        powerUpEnergyPoints.put("Sword", 20);
        powerUpEnergyPoints.put("Photo of Your Dog", 50);
    }

    //need to change so the powerUps are mapped to integers in inventory and user can put number instead of having to type out full name of power up
    public void usePowerUp(String powerUp) {
        if (powerUpEnergyPoints.containsKey(powerUp)) {
            int energyPointsToAdd = powerUpEnergyPoints.get(powerUp);
            energyPoints += energyPointsToAdd;
            System.out.println("Used" + powerUp + ". You gained " + energyPointsToAdd + " energy points.");
        } else {
            System.out.println("Invalid power-up selected.");
        }
    }

    public void revive() {
        if (powerUpEnergyPoints.containsKey("Photo of Your Dog")) {
            hp = 100;
            System.out.println("The cuteness of your dog has brought you back to life. HP is fully restored. \n Keep going!");
        } else {
            System.out.println("You don't have the Photo of Your Dog. Cannot revive.");
        }
    }

    public int getHp() {
        return hp;
    }


    public int getEnergyPoints() {
        return energyPoints;
    }


    public void addPowerUps(PowerUps powerUp) {
        powerUpsInventory.put(powerUp.getName(), powerUp);
        System.out.println("You picked up " + powerUp.getName() + ". Added to your inventory.");
    }

    public void usePowerUps(String powerUpName) {
        if (powerUpsInventory.containsKey(powerUpName)) {
            PowerUps powerUp = powerUpsInventory.get(powerUpName);
            hp += powerUp.getHpEffect();
            energyPoints += powerUp.getEnergyEffect();
            System.out.println("Used " + powerUpName + ". HP restoryed by " + powerUp.getHpEffect() + " points. Energy points increased by " + powerUp.getEnergyEffect() + ".");
        } else {
            System.out.println("Invalid power-up selected");
        }
    }
}

// still need to implement logic for determining type of attack based on energy points 
// need to implement logic for revival/healing 

