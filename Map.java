
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
    private Scanner scanner;


    public Map(int pathChoice, int destinationIndex, Player player) {
        this.pathChoices = paths[pathChoice];
        this.playerIndex = 0;
        this.destinationIndex = destinationIndex;
        this.scanner = new Scanner(System.in);
        initializeEnemies(pathChoice);
        initializePowerUps();
        this.player = player;
    }

   private void initializeEnemies(int pathChoice) {
    locationEnemyMapping.put("Botanical Garden", new EnemyInfo("Ghost of Sylvia Plath", "Bell Jar"));
    locationEnemyMapping.put("Sage Music Hall", new EnemyInfo("Music Teacher", "Violin"));
    locationEnemyMapping.put("Campus Center", new EnemyInfo("Person Who Just Ordered the Last Yerba Mate", "Cold Can of Yerba Mate"));
    locationEnemyMapping.put("Library", new EnemyInfo("Your Ex Who Works in the Cafe", "A Shirt They Stole From You"));
    locationEnemyMapping.put("Seelye Hall", new EnemyInfo("Professor for a Class You Haven't Gone to in Two Weeks", "The Textbook You Never Bought"));
    locationEnemyMapping.put("JMG", new EnemyInfo("Student Doing Chalk Art", "A Box of Broken Chalk"));
    locationEnemyMapping.put("Campus Bookstore", new EnemyInfo("Entire Tour Group", "Complimentary Tote Bag"));
    locationEnemyMapping.put("Ford Hall", new EnemyInfo("Ford Hall Boss", "Automatic A"));

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

            // Check if the player has reached the destination after the encounter
            if (playerIndex >= destinationIndex) {
                break;  // Exit the loop to prevent further unnecessary iterations
            }

            // Continue exploring and check for power-ups
            checkPowerUp();
        }

        if (paths[currentPathIndex][playerIndex].equals("Ford Hall")) {
            encounterBoss();
        } 
    } finally {
        scanner.close();
        System.exit(0);
    }
}


private void encounterBoss() {
    System.out.println("You have encountered the boss! You are so close to class!");
    EnemyInfo bossInfo = locationEnemyMapping.get("Ford Hall");
    Boss boss = new Boss(bossInfo.getEnemyName(), 150, 10);
    startFight(boss);
    if (player.getHp() > 0) {
        System.out.println("Congratulations! You made it to class!");
    } else {
        System.out.println("You were defeated before you could make it to class. Game over.");
    }
}

private void encounterEnemy(EnemyInfo enemyInfo) {
        System.out.println("An enemy has appeared!");
    
        String currentLocation = paths[currentPathIndex][playerIndex];
    
        if (locationEnemyMapping.containsKey(currentLocation)) {
            String enemyName = enemyInfo.getEnemyName();
            System.out.println("Enemy Name: " + enemyName);
    
            if (enemyName.equals("Ford Hall Boss")) {
                Boss boss = new Boss(enemyName, 150, 10);
                startFight(boss);
            } else {
                Enemy enemy = new Enemy(enemyName, 50);
                startFight(enemy);
                dropItem(enemyInfo);
            }
    
            locationEnemyMapping.remove(currentLocation);  // Remove the encountered enemy from the map
        }
    }

private void dropItem(EnemyInfo enemyInfo ) {

    System.out.println(enemyInfo.getEnemyName() + " dropped " + enemyInfo.getDroppedItem() + ". Do you want to pick it up? (1. Yes / 2. No)");
    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice ==1) {
        pickUpItem(enemyInfo.getDroppedItem());
    } else {
        System.out.println("You left" + enemyInfo.getDroppedItem() + " behind.");
    }
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
    boolean itemDropped = false;

    while (player.getHp() > 0 && enemy.getHp() > 0) {
        System.out.println("\nOptions: [1] Attack, [2] Use Power-Up\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                int playerDamage = calculatePlayerDamage();
                enemy.receiveAttack(playerDamage);

                if (enemy.getHp() <= 0 && !itemDropped) {
                    if (paths[currentPathIndex][playerIndex].equals("Ford Hall")) {
                        System.out.println("\nCongratulations! You defeated the boss at Ford Hall! You made it to class on time! \n");
                    } else{
                        System.out.println("\nYou defeated " + enemy.getName());
                        System.out.println(enemy + " has dropped an item.\n");
                        dropItem(locationEnemyMapping.get(paths[currentPathIndex][playerIndex]));
                        itemDropped = true;
                        return;  // Exit the fight loop
                    }
                    return;
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
                break;

            default:
                System.out.println("Invalid choice. Try again.");
                break;
        }
    }
}

private int calculatePlayerDamage() {
    
    if (player.getEnergyPoints()<  20) {
        return 10;
    } else if (player.getEnergyPoints() < 50) {
        return 20;
    } else {
        return 30;
    }

}

private int calculateEnemyDamage() {
    String currentLocation = paths[currentPathIndex][playerIndex];
    EnemyInfo enemyInfo = locationEnemyMapping.get(currentLocation);
    if (enemyInfo != null) {
        if (enemyInfo.getEnemyName().equals("Ford Hall Boss")) {
            return 10;
        } else {
            return 8;
        }
    }
    return 0;
}

private void usePowerUp() {

    System.out.println("Choose a power-up to use:");

    for (String powerUp : player.getPowerUps()) {
        System.out.println(powerUp);
    }

    Scanner scanner = new Scanner(System.in);
    String selectedPowerUp = scanner.nextLine();

    player.usePowerUp(selectedPowerUp);
}

private void checkPowerUp() {
    for (String powerUp : powerUps) {
        if (paths[currentPathIndex][playerIndex].equals(powerUp)) {
            System.out.println("You found " + powerUp + ". Do you want to pick it up? [1] Yes, [2] No");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("You picked up " + powerUp + ".");
                
            } else {
                System.out.println("You left " + powerUp + " behind.");
            }
             scanner.close();
        }
    }
}

public void enterBuilding() {
    
    System.out.println("You entered " + paths[currentPathIndex][playerIndex] + ". Look around for power-ups!");
    checkPowerUp();
}


public void exitBuilding() {
    
    System.out.println("You exited " + paths[currentPathIndex][playerIndex] + ".");
}

public String getCurrentLocation() {
    return paths[currentPathIndex][playerIndex];
}
}

