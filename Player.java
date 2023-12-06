import java.util.Scanner;

public class Player {
    public String name;
    public int health;
    private static Scanner scanner = new Scanner(System.in);
    

    public String setName() {
        System.out.println("Hello. What is your name? ");
        String name = scanner.nextLine();
        System.out.println("Welcome, " + name);
        return(name);
    }

    public Player(String name, int health) {
        this.name = setName();
        this.health = 100;
    }


}