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



    public Player(String playerName, int initialHp) {
        this.playerName = playerName;
        this.hp = initialHp;
        this.energyPoints = 0;
        this.powerUpEnergyPoints = new HashMap<>();
        this.powerUps = new ArrayList<>();
        initializePowerUps();
        System.out.println("\nYour name is: " + playerName + ". Your HP is at " + initialHp);
    }

    public List<String> getPowerUps() {
        return powerUps;
    }


    public void receiveAttack(int damage) {
        // Ensure that the player's health points don't go below zero
        hp = Math.max(0, hp - damage);

        System.out.println(playerName + " received " + damage + " damage. Current HP: " + hp);
    }

    // Add other methods as needed, such as getters and setters

    // Example: Method to restore health points
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
}