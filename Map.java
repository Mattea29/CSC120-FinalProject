public class Map {
   
    // constructor, need graph stuff too 

    private String[][] paths = {
        {"Comstock", "President's House", "Botanical Garden", "Paradise Pond", "Sage Music Hall", "Ford Hall"},
        {"Comstock", "President's House", "Campus Center", "Library", "Seelye Hall", "Ford Hall"},
        {"Comstock", "President's House", "JMG", "College Hall", "Campus Bookstore", "Ford Hall"}
    };

    private String[] currentPath;
    private String[] currentEnemies;
    private String[] currentPowerUps;

    public Map(String[] locations, int destination, int pathIndex) {
        this.currentPath = paths[pathIndex];
    }

    public void move() {

    }

    public void enterBuilding() {

    }

    public void exitBuilding() {
        
    }
}