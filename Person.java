import java.util.Scanner;

public class Person {
    public String name;
    public int health;
    private static Scanner scanner = new Scanner(System.in);
    

    public String setName() {
        System.out.println("Hello. What is your name? ");
        String name = scanner.nextLine();
        System.out.println("Welcome, " + name);
        return(name);
    }

    public void healthPoints() {
        // tracking health points for both player and enemy
    }

    public Person(String name, int health) {
        this.name = setName();
        this.health = health;
    }


}