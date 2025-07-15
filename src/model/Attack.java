package src.model;

/**
 * Repräsentiert einen Angriff eines Monsters im RPG-Manager.
 */
public class Attack {
    private String name;
    private int toHitBonus;
    private String damageFormula;
    private String damageType;
    private Monster monster;

    /**
     * Standardkonstruktor
     */
    public Attack() {
    }

    /**
     * Konstruktor mit grundlegenden Attributen
     *
     * @param name          Name des Angriffs
     * @param toHitBonus    Bonus auf den Angriffswurf
     * @param damageFormula Schadensformel (z.B. "2d6+3")
     * @param damageType    Schadenstyp (z.B. "Feuer", "Kälte", "Physisch")
     */
    public Attack(String name, int toHitBonus, String damageFormula, String damageType) {
        this.name = name;
        this.toHitBonus = toHitBonus;
        this.damageFormula = damageFormula;
        this.damageType = damageType;
    }

    /**
     * Konstruktor mit allen Attributen
     *
     * @param name          Name des Angriffs
     * @param toHitBonus    Bonus auf den Angriffswurf
     * @param damageFormula Schadensformel (z.B. "2d6+3")
     * @param damageType    Schadenstyp (z.B. "Feuer", "Kälte", "Physisch")
     * @param monster       Monster, zu dem der Angriff gehört
     */
    public Attack(String name, int toHitBonus, String damageFormula, String damageType, Monster monster) {
        this.name = name;
        this.toHitBonus = toHitBonus;
        this.damageFormula = damageFormula;
        this.damageType = damageType;
        this.monster = monster;
    }

    /**
     * Simuliert einen Angriffswurf
     *
     * @return Ergebnis des Angriffswurfs
     */
    public int rollAttack() {
        // Simuliert einen d20-Wurf + Angriffsbonus
        return (int) (Math.random() * 20) + 1 + toHitBonus;
    }

    /**
     * Simuliert einen Schadenswurf basierend auf der Schadensformel
     *
     * @return Ergebnis des Schadenswurfs
     */
    public int rollDamage() {
        // Einfache Implementierung für Schadensberechnung
        // In einer vollständigen Implementierung würde hier die Schadensformel geparst
        // und ausgewertet werden
        return (int) (Math.random() * 10) + 1;
    }

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getToHitBonus() {
        return toHitBonus;
    }

    public void setToHitBonus(int toHitBonus) {
        this.toHitBonus = toHitBonus;
    }

    public String getDamageFormula() {
        return damageFormula;
    }

    public void setDamageFormula(String damageFormula) {
        this.damageFormula = damageFormula;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "name='" + name + '\'' +
                ", toHitBonus=" + toHitBonus +
                ", damageFormula='" + damageFormula + '\'' +
                ", damageType='" + damageType + '\'' +
                '}';
    }
}
