import java.util.Scanner;

public class Command {

    private static boolean inventoryVisible = false;

    public static char getUserCommand(Scanner scanner) {
        System.out.println("Press 'x' to open/close inventory, 'h'for help, or 'q' to quit.");
        String input = scanner.nextLine();
        return input.isEmpty() ? ' ' : Character.toUpperCase(input.charAt(0));
    }

    public static boolean executeCommand(char command, Inventory inventory, Map map) {
        switch (command) {
            case 'X':
                toggleInventory(inventory);
                return true;
            case 'H':
                displayHelpMenu();
                return true;
            case 'Q':
                System.out.println("Quitting the game. Goodbye!");
                System.exit(0); 
                return false;
            default:
                System.out.println("Invalid input. Continuing with the game.");
                return true;
        }   
    }

    private static void toggleInventory(Inventory inventory) {
        inventoryVisible = !inventoryVisible;
        if (inventoryVisible) {
            inventory.displayInventory();
        } else {
            System.out.println("Inventory closed");
        }

    }

    private static void displayHelpMenu() {
        System.out.println("Help Menu:");
        System.out.println("Hello. Stuff will be put here to help you!");
    }
}