
public class Enemy {
    
    private String name;
    private int hp;

    public Enemy(String name, int initialHp) {
        this.name = name;
        this.hp = initialHp;
    }

    public void receiveAttack(int damage) {
        hp -= damage;
        System.out.println(name + "received " + damage + " damage. " + name + " HP is now: " + hp);
        if (hp <= 0) {
            System.out.println(name + " defeated!");
        }
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return name; 
    }
}