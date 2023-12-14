import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Game {


    private static final String[] powerUps = {"Water Bottle", "Protein Bar", "Lip Balm", "Sword", "Photo of Your Dog"};
    private static ArrayList<String> selectedPowerUps = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public String name; 
    private static HashMap<String, String> powerUpDescriptions = new HashMap<>();


    static {
        powerUpDescriptions.put("Water Bottle", "A refreshing bottle of several days old water. Drink up!");
        powerUpDescriptions.put("Protein Bar", "A delicious protein bar that you found smushed at the bottom of your bag. Yum!");
        powerUpDescriptions.put("Lip Balm", "Moisturize those lips! The overwhelming menthol smell invigorates you.");
        powerUpDescriptions.put("Sword", "Why do you even have this? It's dangerous to go alone! Take this.");
        powerUpDescriptions.put("Photo of Your Dog", "So cute! Brings a tear to your eyes.");
    }
   

    public static boolean enter() {
        System.out.println("\nDo You Want to Play? (1. Yes / 2. No)\n");
        int response = scanner.nextInt();
        return response == 1;
    }

    public static void selectPowerUps(Inventory inventory) {
        List<String> availablePowerUps = new ArrayList<>(Arrays.asList(powerUps));
        int itemsToSelect = 3;
    
        boolean readyToGo = false;
        System.out.println("\nYou have a long journey ahead. On your bed are 5 items. Choose wisely.\n");
        while (!readyToGo) {
            for (int i = 0; i < itemsToSelect; i++) {
                System.out.println("Choose " + (itemsToSelect - i) + " more item(s).");

                
    
                // Display the available power-ups
                for (int j = 0; j < availablePowerUps.size(); j++) {
                    System.out.println((j + 1) + ". " + availablePowerUps.get(j));
                }
    
                // Ask the user to select an item 
                System.out.println("\nEnter the number of the item you want to select (1-" + availablePowerUps.size() + "): ");
                int selectedItemIndex = scanner.nextInt();
                scanner.nextLine(); 
    
                // Validate the input
                if (selectedItemIndex < 1 || selectedItemIndex > availablePowerUps.size()) {
                    System.out.println("\nInvalid selection. Please choose a number between 1 and " + availablePowerUps.size() + ".");
                    i--;  
                    continue;
                }
    
                String selectedPowerUp = availablePowerUps.get(selectedItemIndex - 1);
                String description = powerUpDescriptions.get(selectedPowerUp);
    
                // Display the description
                System.out.println(description);
    
                // Ask if the user wants to add it to their inventory
                System.out.println("\nDo you want to add " + selectedPowerUp + " to your inventory? (1. Yes / 2. No)\n");
                int response = scanner.nextInt();
                scanner.nextLine(); 
    
                if (response == 1) {
                    selectedPowerUps.add(selectedPowerUp);
                    System.out.println(selectedPowerUp + " added to your inventory.\n");
                    availablePowerUps.remove(selectedPowerUp);
                } else {
                    System.out.println(selectedPowerUp + " not added to your inventory.\n");
                    i--;  // Decrement the loop counter to allow the user to reselect
                }
            }
    
            // Ask if the user is ready to go
            System.out.println("\nAre you ready to go? (1. Yes / 2. No)\n");
            int readyResponse = scanner.nextInt();
            scanner.nextLine();  
    
            if (readyResponse == 1) {
                readyToGo = true;
                System.out.println("\nOk, let's go!\n");
            } else {
                System.out.println("\nYou can reselect items or perform other actions.\n");
            }
        }
    }

    private static int selectPath(Scanner scanner) {
        int pathChoice;
        do{
            System.out.println("\n Choose your path:\n ");
            System.out.println("[1] Path by Paradise Pond\n");
            System.out.println("[2] Path through center campus\n");
            System.out.println("[3] Strange path\n");
            System.out.println("Enter the number of chosen path (1-3):\n");
            pathChoice = scanner.nextInt();
            scanner.nextLine();
            switch (pathChoice) {
                case 1:
                    System.out.println("\nYou've selected the scenic route. This takes you along paradise pond!\n");
                    break;
                case 2:
                    System.out.println("\nYou've selected the busy path. This takes you through the center of campus -- perfect for people watching! This path may be deceptively simple\n");
                    break;
                case 3:
                    System.out.println("\nYou've selected a very odd route. I'm not sure why you're going this way.\n");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 3.\n");
            }
        } while (pathChoice < 1 || pathChoice > 3);
        return pathChoice;
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Journey to Ford!\n");
        if (!enter()) {
            System.out.println("Ok, Goodbye!");
            System.exit(0);
        }
        System.out.println("\nWhat is your name?\n");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 100);
        Inventory playerInventory = new Inventory();
        System.out.println("Loading Game...");
        System.out.println("Ready!");
        System.out.println("It's another beautiful morning at Smith College. From your dorm room in Comstock House, you can hear birds chirping as well as the beautiful noise of your next door neighbor blowdrying their hair.");
        System.out.println(" 'Ugh', you think, 'who the heck is up this early blowdrying their hair?' It can't be later than 8:00 AM, the time you typically wake up to get to your favorite class: Object Oriented Programming with Jordan and Johanna");
        System.out.println("Actually, now that you think about it, it's eerily quiet for what is usually a busy time of people rushing to get ready for their morning classes. You decide to check your phone.");
        System.out.println("Holy Shit! It's 9:05. Your class starts at 9:25 and you take a while to get ready! You have to hurry!");
        selectPowerUps(playerInventory);
        int pathChoice = selectPath(scanner);
        Map map = new Map(pathChoice - 1, 5, player);
        map.move(pathChoice);
}
}