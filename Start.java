// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.Map;
// import java.util.HashMap;

// public class Start {

//     private static final String[] powerUps = {"Water Bottle", "Protein Bar", "Lip Balm", "Sword", "Photo of Your Dog"};
//     private static ArrayList<String> selectedPowerUps = new ArrayList<>();
//     private static Scanner scanner = new Scanner(System.in);
//     public String name; 
//     private static Map<String, String> powerUpDescriptions = new HashMap<>();


//     static {
//         powerUpDescriptions.put("Water Bottle", "A refreshing bottle of several days old water. Drink up!");
//         powerUpDescriptions.put("Protein Bar", "A delicious protein bar that you found smushed at the bottom of your bag. Yum!");
//         powerUpDescriptions.put("Lip Balm", "Moisturize those lips! The overwhelming menthol smell invigorates you.");
//         powerUpDescriptions.put("Sword", "Why do you even have this? It's dangerous to go alone! Take this.");
//         powerUpDescriptions.put("Photo of Your Dog", "So cute! Brings a tear to your eyes.");
//     }
   

//     public static void enter() {
//         String response = scanner.nextLine();
//         if (response.equals("Yes")){
//             System.out.println("Loading Game...");
//             System.out.println("Ready!");
//             System.out.println("It's another beautiful morning at Smith College. From your dorm room in Comstock House, you can hear birds chirping as well as the beautiful noise of your next door neighbor blowdrying their hair.");
//             System.out.println(" 'Ugh', you think, 'who the heck is up this early blowdrying their hair?' It can't be later than 8:00 AM, the time you typically wake up to get to your favorite class: Object Oriented Programming with Jordan and Johanna");
//             System.out.println("Actually, now that you think about it, it's eerily quiet for what is usually a busy time of people rushing to get ready for their morning classes. You decide to check your phone.");
//             System.out.println("Holy Shit! It's 9:05. Your class starts at 9:25 and you take a while to get ready! You have to hurry!");
       
//         } else {
//             System.out.println("Ok, Goodbye.");
//             System.exit(0);
//         };
//     }

//     public static void setName() {
//         System.out.println("What is your name? ");
//         String player = scanner.nextLine();
//         System.out.printf("Good morning, " + player + "!");
//     }

//     public static void selectPowerUps() {
//         int itemsToSelect = 3;
    
//         boolean readyToGo = false;
    
//         while (!readyToGo) {
//             for (int i = 0; i < itemsToSelect; i++) {
//                 System.out.println("Laying before you on your bed are 5 items. You have a long day ahead of you. Choose " + (itemsToSelect - i) + " more item(s).");
    
//                 // Display the available power-ups
//                 for (int j = 0; j < powerUps.length; j++) {
//                     System.out.println((j + 1) + ". " + powerUps[j]);
//                 }
    
//                 // Ask the user to select an item
//                 System.out.println("Enter the number of the item you want to select (1-" + powerUps.length + "): ");
//                 int selectedItemIndex = scanner.nextInt();
//                 scanner.nextLine();  // Consume the newline character
    
//                 // Validate the input
//                 if (selectedItemIndex < 1 || selectedItemIndex > powerUps.length) {
//                     System.out.println("Invalid selection. Please choose a number between 1 and " + powerUps.length + ".");
//                     i--;  // Decrement the loop counter to allow the user to reselect
//                     continue;
//                 }
    
//                 String selectedPowerUp = powerUps[selectedItemIndex - 1];
//                 String description = powerUpDescriptions.get(selectedPowerUp);
    
//                 // Display the description
//                 System.out.println(description);
    
//                 // Ask if the user wants to add it to their inventory
//                 System.out.println("Do you want to add " + selectedPowerUp + " to your inventory? (1. Yes / 2. No)");
//                 int response = scanner.nextInt();
//                 scanner.nextLine();  // Consume the newline character
    
//                 if (response == 1) {
//                     selectedPowerUps.add(selectedPowerUp);
//                     System.out.println(selectedPowerUp + " added to your inventory.");
//                 } else {
//                     System.out.println(selectedPowerUp + " not added to your inventory.");
//                     i--;  // Decrement the loop counter to allow the user to reselect
//                 }
//             }
    
//             // Ask if the user is ready to go
//             System.out.println("Are you ready to go? (1. Yes / 2. No)");
//             int readyResponse = scanner.nextInt();
//             scanner.nextLine();  // Consume the newline character
    
//             if (readyResponse == 1) {
//                 readyToGo = true;
//                 System.out.println("Ok, let's go!");
//             } else {
//                 System.out.println("You can reselect items or perform other actions.");
//             }
//         }
//     }
    


    

//     public static void main(String[] args) {
//         setName();
//         System.out.println("Do You Want to Play?");
//         System.out.println("Please Type: \n Yes or No");
//         enter();
//         System.out.println("After quickly washing your face and brushing your teeth, you get dressed in a stylish yet comfortable outfit (aka sweatpants and a stained t-shirt).");
//         selectPowerUps();
        
//     }
    
// }