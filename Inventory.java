import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> items;
    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public void displayInventory() {
        System.out.println("Inventory");
        for (String item : items) {
            System.out.println(item);
        }
    }
    
}
