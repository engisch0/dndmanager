package src.model;

/**
 * Abstrakte Basisklasse für alle Gegenstände im RPG-Manager.
 * Implementiert gemeinsame Attribute und Methoden für verschiedene
 * Gegenstandstypen.
 */
public abstract class Item {

    private String itemId;
    private String name;
    private String description;
    private double weight;
    private int value;

    /**
     * Standardkonstruktor
     */
    public Item() {

    }

    /**
     * Konstruktor mit grundlegenden Attributen
     *
     * @param itemId      Eindeutige Gegenstands-ID
     * @param name        Name des Gegenstands
     * @param description Beschreibung des Gegenstands
     */
    public Item(String itemId, String name, String description) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.weight = 1.0;
        this.value = 0;
    }

    /**
     * Konstruktor mit allen Attributen
     *
     * @param itemId      Eindeutige Gegenstands-ID
     * @param name        Name des Gegenstands
     * @param description Beschreibung des Gegenstands
     * @param weight      Gewicht des Gegenstands
     * @param value       Wert des Gegenstands in Gold
     */
    public Item(String itemId, String name, String description, double weight, int value) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    // Getter und Setter
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return itemId != null ? itemId.equals(item.itemId) : item.itemId == null;
    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }
}