import java.util.Scanner;

public class Start {

    public String name; 
    static Scanner myObj = new Scanner(System.in);

    public static void enter() {
        String response = myObj.nextLine();
        if (response.equals("Yes")){
            System.out.println("Loading Game...");
            System.out.println("Ready!");
        } else {
            System.out.println("Ok, Goodbye.");
            System.exit(0);
        };
    }



    public static void main(String[] args) {
        System.out.println("Welcome to IDKYET! Do You Want to Enter?");
        System.out.println("\n Please Type: \n [Yes] or [No]");
        enter();
       
    }
    
}