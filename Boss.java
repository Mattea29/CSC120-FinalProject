public class Boss extends Enemy {

    private int turnsCounter;

    public Boss(String name, int maxHp, int damage) {
        super(name, maxHp);
        this.setDamage(damage);
        this.turnsCounter = 0;
    }

    public void incrementTurnsCounter() {
        turnsCounter++;
    }

    public boolean specialAttackReady() {
        int turnsBeforeSpecialAttack = 4;
        return turnsCounter >= turnsBeforeSpecialAttack;
    }

    public void resetTurnsCounter() {
        turnsCounter = 0;
    }
}