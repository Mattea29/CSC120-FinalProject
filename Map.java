
import java.util.Scanner;
import java.util.HashMap;

public class Map {
   
    private int currentPathIndex;
    private String[] pathChoice;
    private int playerIndex;
    private int destinationIndex;
    private int stepsTaken;
    private String[] enemies;
    private String[] powerUps;
    private boolean enemiesEncountered;
    private HashMap<String, String> locationEnemyMapping = new HashMap<>();

    private String[][] paths = {
        {"Comstock", "President's House", "Botanical Garden", "Paradise Pond", "Sage Music Hall", "Ford Hall"},
        {"Comstock", "President's House", "Campus Center", "Library", "Seelye Hall", "Ford Hall"},
        {"Comstock", "President's House", "JMG", "College Hall", "Campus Bookstore", "Ford Hall"}
    };


    public Map(int pathChoice, int destinationIndex) {
        this.pathChoice = paths[pathChoice];
        this.playerIndex = 0;
        this.destinationIndex = destinationIndex;
        this.stepsTaken = 0;
        initializeEnemies(pathChoice);
        initializePowerUps();
    }

   private void initializeEnemies(int pathChoice) {
    locationEnemyMapping.put("Botanical Garden", "Ghost of Sylvia Plath");
    locationEnemyMapping.put("Sage Music Hall", "Music Teacher");
    locationEnemyMapping.put("Campus Center", "Person Who Just Ordered the Last Yerba Mate");
    locationEnemyMapping.put("Library", "Your Ex Who Works in the Cafe");
    locationEnemyMapping.put("Seelye Hall", "Professor for a Class You Haven't Gone to in Two Weeks");
    locationEnemyMapping.put("JMG", "Student Doing Chalk Art");
    locationEnemyMapping.put("Campus Bookstore", "Entire Tour Group");

   }

   private void printEnemies() {
    System.out.println("Enemies on this path:");
    for (String location : locationEnemyMapping.keySet()) {
        System.out.println(location + ": " + locationEnemyMapping.get(location));
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

        printEnemies(); 

        // if (!enemiesEncountered) {
        //     initializeEnemies(pathChoice);
        //     printEnemies();
        //     enemiesEncountered = true;
        // }
        
        // while (playerIndex < destinationIndex) {
        //     System.out.println("Current Location: " + paths[currentPathIndex][playerIndex]);
        //     System.out.println("Options: [1] Move forward, [2] Move backward");

       
        //     int choice = scanner.nextInt();

        //     if (choice == 1) {
        //         playerIndex = (playerIndex + 1) % paths[currentPathIndex].length;
        //     } else if (choice == 2) {
        //         playerIndex = (playerIndex - 1 + paths[currentPathIndex].length) % paths[currentPathIndex].length;
        //     } else {
        //         System.out.println("Invalid choice. Try again.");
        //         return;
        //     }
  

        //     if (!enemiesEncountered) {
        //         for (int i = 0; i < enemies.length; i++) {
        //             if (enemies[i] != null && paths[currentPathIndex][playerIndex].equalsIgnoreCase(enemies[i])) {
        //                 encounterEnemy();
        //                 enemiesEncountered = true;
        //                 break;
        //             }
        //         }
        //     }
        
    //     if (playerIndex == destinationIndex) {
    //     //encounter boss enemy 
    //         System.out.println("Current Location: " + paths[currentPathIndex][playerIndex]);
    //         encounterEnemy();
    //         System.exit(0);
    //     } else {
    //         checkPowerUp();
    //     }

    //     }
    
   
    // }
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
        if (locationEnemyMapping.containsKey(currentLocation)) {
            String enemy = locationEnemyMapping.get(currentLocation);
            encounterEnemy(enemy);
            locationEnemyMapping.remove(currentLocation);  // Remove the encountered enemy from the map
        }

        if (playerIndex == destinationIndex) {
            // Encounter boss enemy
            System.out.println("Current Location: " + paths[currentPathIndex][playerIndex]);
            encounterEnemy("Boss Enemy");
            System.exit(0);
        } else {
            checkPowerUp();
        }
    }
}


private void encounterEnemy(String enemy) {
    System.out.println("An enemy has appeared!");
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

