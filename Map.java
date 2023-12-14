
import java.util.Scanner;
import java.util.HashMap;


public class Map {
   
    private int currentPathIndex;
    private String[] pathChoices;
    private int playerIndex;
    private int destinationIndex;
    private String[] powerUps;
    private HashMap<String, EnemyInfo> locationEnemyMapping = new HashMap<>();
    private Player player;


    

    private String[][] paths = {
        {"Comstock", "President's House", "Botanical Garden", "Paradise Pond", "Sage Music Hall", "Ford Hall"},
        {"Comstock", "President's House", "Campus Center", "Library", "Seelye Hall", "Ford Hall"},
        {"Comstock", "President's House", "JMG", "College Hall", "Campus Bookstore", "Ford Hall"}
    };


    public Map(int pathChoice, int destinationIndex, Player player) {
        this.pathChoices = paths[pathChoice];
        this.playerIndex = 0;
        this.destinationIndex = destinationIndex;
        initializeEnemies(pathChoice);
        initializePowerUps();
        this.player = player;
    }

   private void initializeEnemies(int pathChoice) {
    locationEnemyMapping.put("Botanical Garden", new EnemyInfo("Ghost of Sylvia Plath", "Poetry Book"));
    locationEnemyMapping.put("Sage Music Hall", new EnemyInfo("Music Teacher", "Violin"));
    locationEnemyMapping.put("Campus Center", new EnemyInfo("Person Who Just Ordered the Last Yerba Mate", "Cold Can of Yerba Mate"));
    locationEnemyMapping.put("Library", new EnemyInfo("Your Ex Who Works in the Cafe", "A Shirt They Stole From You"));
    locationEnemyMapping.put("Seelye Hall", new EnemyInfo("Professor for a Class You Haven't Gone to in Two Weeks", "The Textbook You Never Bought"));
    locationEnemyMapping.put("JMG", new EnemyInfo("Student Doing Chalk Art", "A Box of Broken Chalk"));
    locationEnemyMapping.put("Campus Bookstore", new EnemyInfo("Entire Tour Group", "Complimentary Tote Bag"));

   }

   private class EnemyInfo {
    private String enemyName;
    private String droppedItem;

    public EnemyInfo(String enemyName, String droppedItem) {
        this.enemyName = enemyName;
        this.droppedItem = droppedItem;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getDroppedItem() {
        return droppedItem;
    }
   }

    private void initializePowerUps() {
        int numPowerUps = 3;
        powerUps = new String[numPowerUps];
        for (int i = 0; i < numPowerUps; i++) {
            powerUps[i] = "PowerUp" + (i + 1);
        }
   }

   public void move(int pathChoice) {
    Scanner scanner = new Scanner(System.in);

    try {
        switch (pathChoice) {
            case 1:
                currentPathIndex = 0;
                break;
            case 2:
                currentPathIndex = 1;
                break;
            case 3:
                currentPathIndex = 2;
                break;
            default:
                System.out.println("Invalid path choice");
                return;
        }

        while (playerIndex < destinationIndex) {
            System.out.println("Current Location: " + paths[currentPathIndex][playerIndex]);
            System.out.println("Options: [1] Move forward, [2] Move backward");

            int choice = scanner.nextInt();

            if (choice == 1) {
                playerIndex = (playerIndex + 1) % paths[currentPathIndex].length;
            } else if (choice == 2) {
                playerIndex = (playerIndex - 1 + paths[currentPathIndex].length) % paths[currentPathIndex].length;
            } else {
                System.out.println("Invalid choice. Try again.");
                return;
            }

            // Check for enemy encounters in the current location
            String currentLocation = paths[currentPathIndex][playerIndex];
            EnemyInfo enemyInfo = locationEnemyMapping.get(currentLocation);
            if (enemyInfo != null) {
                enemyInfo.getEnemyName();
                encounterEnemy(enemyInfo);
                locationEnemyMapping.remove(currentLocation);  // Remove the encountered enemy from the map
            }

            if (playerIndex == destinationIndex) {
                // Encounter boss enemy
                System.out.println("Current Location: " + paths[currentPathIndex][playerIndex]);
                encounterEnemy(enemyInfo);
            } else {
                checkPowerUp();
            }
        }
    } finally {
        scanner.close();
        System.exit(0);
    }
}


private void encounterEnemy(EnemyInfo enemyInfo) {
    System.out.println("An enemy has appeared!");

    String currentLocation = paths[currentPathIndex][playerIndex];

    if (locationEnemyMapping.containsKey(currentLocation)) {
        String enemyName = enemyInfo.getEnemyName();
        System.out.println("Enemy Name: " + enemyName);

        // Initialize the enemy and start the fight
        new HashMap<>();
        Enemy enemy = new Enemy(enemyName, 50); 
        startFight(enemy);

        // if Enemy defeated, drop item 
        dropItem(enemyInfo);
    }
}

private void dropItem(EnemyInfo enemyInfo ) {

    System.out.println(enemyInfo.getEnemyName() + " dropped " + enemyInfo.getDroppedItem() + ". Do you want to pick it up? (1. Yes / 2. No)");
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();

    if (choice ==1) {
        pickUpItem(enemyInfo.getDroppedItem());
    } else {
        System.out.println("You left" + enemyInfo.getDroppedItem() + " behind.");
    }
    scanner.close();
}

private String determineDroppedItem(String enemyName) {
    if (locationEnemyMapping.containsKey(enemyName)) {
        EnemyInfo enemyInfo = locationEnemyMapping.get(enemyName);
        return enemyInfo.getDroppedItem();
    } else {
        return "Unknown Item";
    }
}

private void pickUpItem(String item) {
    if (player.getInventorySize() < Player.MAX_INVENTORY_SIZE) {
        player.addToInventory(item);
        System.out.println("You picked up " + item + " and added it to your inventory.");
    } else {
        System.out.println("Your inventory is full. You cannot pick up " + item + ".");
    }
}

private void startFight(Enemy enemy) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("Options: [1] Attack, [2] Use Power-Up");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                int playerDamage = calculatePlayerDamage();
                enemy.receiveAttack(playerDamage);

                if (enemy.getHp() <= 0) {
                    System.out.println("You defeated " + enemy.getName());
                    System.out.println(enemy + " has dropped an item."); 
                    return;  // Exit the fight loop
                }

                int enemyDamage = calculateEnemyDamage();
                player.receiveAttack(enemyDamage);

                if (player.getHp() <= 0) {
                    System.out.println("You were defeated by " + enemy.getName() + ". Game over!");
                    System.exit(0);
                }
                break;

            case 2:
                usePowerUp();
                int enemyAttack = calculateEnemyDamage();
                player.receiveAttack(enemyAttack);
                break;

            default:
                System.out.println("Invalid choice. Try again.");
                break;
        }
        scanner.close();
    } 
}

private int calculatePlayerDamage() {
    // Implement logic to calculate player damage based on player's stats and power-ups
    
    if (player.getEnergyPoints()<  20) {
        return 10;
    } else if (player.getEnergyPoints() < 50) {
        return 20;
    } else {
        return 30;
    }
     // Placeholder value, replace with actual logic
}

private int calculateEnemyDamage() {
    // Implement logic to calculate enemy damage based on enemy's stats
    return 8; // Placeholder value, replace with actual logic
}

private void usePowerUp() {
    // Implement logic to allow the player to use a power-up
    System.out.println("Choose a power-up to use:");

    for (String powerUp : player.getPowerUps()) {
        System.out.println(powerUp);
    }

    Scanner scanner = new Scanner(System.in);
    String selectedPowerUp = scanner.nextLine();

    player.usePowerUp(selectedPowerUp);
    scanner.close();
}

private void checkPowerUp() {
    for (String powerUp : powerUps) {
        if (paths[currentPathIndex][playerIndex].equals(powerUp)) {
            System.out.println("You found " + powerUp + ". Do you want to pick it up? [1] Yes, [2] No");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("You picked up " + powerUp + ".");
                // Add logic to handle the picked up power-up (e.g., add to inventory)
            } else {
                System.out.println("You left " + powerUp + " behind.");
            }
             scanner.close();
        }
    }
}

public void enterBuilding() {
    // Implement logic to allow the player to enter a building
    System.out.println("You entered " + paths[currentPathIndex][playerIndex] + ". Look around for power-ups!");
    checkPowerUp();
}


public void exitBuilding() {
    // Implement logic to allow the player to exit a building
    System.out.println("You exited " + paths[currentPathIndex][playerIndex] + ".");
}

public String getCurrentLocation() {
    return paths[currentPathIndex][playerIndex];
}
}

