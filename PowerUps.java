public class PowerUps {

    private String name;
    private int hpEffect;
    private int energyEffect;


    public PowerUps(String name, int hpEffect, int energyEffect) {
        this.name = name;
        this.hpEffect = hpEffect;
        this.energyEffect = energyEffect;
    }

    public String getName() {
        return name;
    }

    public int getHpEffect() {
        return hpEffect;
    }

    public int getEnergyEffect() {
        return energyEffect;
    }
}