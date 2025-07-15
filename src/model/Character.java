package src.model;

import java.util.HashMap;

/**
 * Klasse, die die Spielcharaktare repräsentieren
 * 
 * @since M1, aber Verwendung erst in M2
 */
public class Character {
    /** Eine eindeutige ID für das Charakter */
    private int characterId;
    /** Name des Charakters */
    private String name;
    /** Art/Herkunft/Gattung des Charakters */
    private String race;
    /** Alter des Charakters */
    private int alter;
    /** Stufe des Charakters */
    private int level;
    /** Größe des Charakters */
    private double size;
    /** Rüstungsklasse des Charakters */
    private int armorClass;
    /** Lebenspunkte des Charakters */
    private int healthPoints;
    /** Attribute des Charakter */
    @SuppressWarnings("unused")
    private HashMap<String, Integer> attributes;

    /**
     * Erstellt ein Objekt von Typ Charakter
     * 
     * @param characterId
     * @param name
     * @param race
     * @param age
     * @param size
     */
    public Character(int characterId, String name, String race, int age, double size) {
        this.characterId = characterId;
        this.name = name;
        this.race = race;
        this.level = 1;
        this.alter = age;
        this.size = size;
        this.armorClass = 10;
        this.healthPoints = 10;
        this.attributes = new HashMap<>();
    }

    // Getter und Setter
    public int getCharacterId() {
        return characterId;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAlter() {
        return alter;
    }

    public double getSize() {
        return size;
    }

    public void setCharacterId(int charakterID) {
        this.characterId = charakterID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String art) {
        this.race = art;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public void setSize(double groesse) {
        this.size = groesse;
    }

    // Methoden
    @Override
    public String toString() {
        return "Charakter{" +
                "charakterId='" + characterId + '\'' +
                ", name='" + name + '\'' +
                ", art='" + race + '\'' +
                ", charakterKlasse='" + race + '\'' +
                ", stufe=" + level +
                ", lebenspunkte=" + healthPoints +
                ", ruestungsklasse=" + armorClass +
                '}';
    }
}
