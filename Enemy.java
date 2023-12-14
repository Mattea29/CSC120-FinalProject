
public class Enemy {
    
    private String name;
    private int maxHp;
    private int damage;

    public Enemy(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
    }

    public void receiveAttack(int damage) {
        maxHp -= damage;
        maxHp = Math.max(0, maxHp);
        System.out.println(name + "received " + damage + " damage. " + name + " HP is now: " + maxHp);
        if (maxHp <= 0) {
            System.out.println(name + " defeated!");
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return name; 
    }
}