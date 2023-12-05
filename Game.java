import java.util.Random;
import java.util.Scanner;

public class Game {

    static class Campus {
        String[] locations;
        int playerLocation;
        int destination;
        int stepsTaken;

        public Campus(String[] locations, int destination) {
            this.locations = locations;
            this.destination = destination;
            this.playerLocation = 0;
            this.stepsTaken = 0;

        }

        public String getCurrentLocation() {
            return locations[playerLocation];
        }

        public void movePlayer() {
            playerLocation++;
            stepsTaken++;

            if (stepsTaken % 5 == 0) {
                encounterEnemy();
                
            }

            if (playerLocation == destination) {
                System.out.println("Congrats! You made it to class!");
                System.exit(0);
            }
        }
        private void encounterEnemy() {
            Random random = new Random();
            boolean enemyAppeared = random.nextBoolean();

            if (enemyAppeared) {
                System.out.println("Oh no! An enemy appeared. You must decide what to do.");
                System.out.print("Options: [1] Fight, [2] Run\nYour choice: ");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("You bravely fought the enemy and continue your journey.");
                } else if (choice == 2) {
                    System.out.println("You ran away from the enemy and continue your journey.");
                } else {
                    System.out.println("Invalid choice. The enemy caught you!");
                    System.out.println("Game Over. Try again.");
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {

        Start.enter();
        String[] campusLocations = {"Entrance", "Main Hall", "Library", "Classrooms", "Cafeteria", "Destination"};

        // Create campus with a destination at index 5 (Destination)
        Campus campus = new Campus(campusLocations, 5);

        // Game loop
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Campus Adventure Game!");
        System.out.println("You are currently at: " + campus.getCurrentLocation());

        while (true) {
            System.out.print("Options: [1] Move forward\nYour choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                campus.movePlayer();
                System.out.println("You are now at: " + campus.getCurrentLocation());
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
    