import java.util.Scanner;
import java.util.List;

public class Command {

    private static boolean inventoryVisible = false;
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserCommand(Scanner scanner) {
        System.out.println("Type 'i' to open/close inventory, 'h'for help, 'u' to use item, or 'q' to quit.");
        return scanner.nextLine().toLowerCase();
    }

    public static boolean executeCommand(char command, String userCommand, Inventory inventory, Map map, Player player) {
        switch (command) {
            case 'i':
                toggleInventory(inventory);
                return true;
            case 'h':
                displayHelpMenu();
                return true;
            case 'q':
                System.out.println("Quitting the game. Goodbye!");
                return false;
            case 'u':
                usePowerUp(player);
                return true;
            default:
                System.out.println("Invalid input. Continuing with the game.");
                return true;
        }   
    }

    public static void toggleInventory(Inventory inventory) {
        inventoryVisible = !inventoryVisible;
        if (inventoryVisible) {
            inventory.displayInventory();
        } else {
            System.out.println("Inventory closed");
        }

    }

    public static void displayHelpMenu() {
        System.out.println("Help Menu:");
        System.out.println("Hello. Stuff will be put here to help you!");
    }

    public static void displayInventory(Player player) {
        List<String> playerInventory = player.getInventory();
        System.out.println("Inventory: ");
        for (String item : playerInventory) {
            System.out.println(item);
        }
    }

    public static void usePowerUp(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a power-up to use:");
        for (String powerUp : player.getPowerUps()) {
            System.out.println(powerUp);
        }
        String selectedPowerUp = scanner.nextLine();
        player.usePowerUp(selectedPowerUp);
    }
}

// previous attempts made it so that the command options were only useable once and then disappeared afterwards 
// need to make it so that the 'HELP' is always an option to users, and if the user puts in certain keys at any point, they can toggle inventory or quit game