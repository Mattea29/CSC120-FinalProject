
import java.util.Scanner;

public class Map {
   
    private int currentPathIndex;
    private String[] pathChoice;
    private int playerIndex;
    private int destinationIndex;
    private int stepsTaken;
    private String[] enemies;
    private String[] powerUps;

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
        initializeEnemies();
        initializePowerUps();
    }

   private void initializeEnemies() {
    int numEnemies = 2;
    int distanceBetweenEnemies = paths[currentPathIndex].length / (numEnemies + 1);
    enemies = new String[numEnemies];

    for (int i = 0; i < numEnemies; i ++) {
        enemies[i] = "Enemy" + (i + 1);
    }
}

    private void initializePowerUps() {
        int numPowerUps = 3;
        powerUps = new String[numPowerUps];
        for (int i = 0; i < numPowerUps; i++) {
            powerUps[i] = "PowerUp" + (i + 1);
        }
   }

   public void move() {
    Scanner scanner = new Scanner(System.in);

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

    stepsTaken++;

    encounterEnemy();

    if (playerIndex == destinationIndex) {
        System.out.println("Congrats! You made it to the destination!");
        System.exit(0);
    } else {
        checkPowerUp();
    }
}


 

private void encounterEnemy() {
    for (String enemy : enemies) {
        if (paths[currentPathIndex][playerIndex].equals(enemy)) {
            System.out.println("Oh no! " + enemy + " appeared. You must decide what to do.");
            System.out.print("Options: [1] Fight, [2] Run\nYour choice: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("You bravely fought " + enemy + " and continue your journey.");
            } else if (choice == 2) {
                System.out.println("You ran away from " + enemy + " and continue your journey.");
            } else {
                System.out.println("Invalid choice. " + enemy + " caught you!");
                System.out.println("Game Over. Try again.");
                System.exit(0);
            }
        }
    }
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

