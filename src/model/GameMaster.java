 
package src.model;
import java.util.ArrayList;
/**
 * Repräsentiert einen Spielleiter im RPG-Manager.
 * Erweitert die abstrakte User-Klasse und fügt spielleiterspezifische Funktionalitäten hinzu.
 */
public class GameMaster extends User {
    private ArrayList<Campaign> campaigns;
    private ArrayList<Monster> monsters;
    private ArrayList<Item> items;
    
    /**
     * Konstruktor mit allen Attributen
     * 
     * @param userId Eindeutige Benutzer-ID
     * @param username Benutzername
     * @param password Passwort
     * @param email E-Mail-Adresse
     */
    public GameMaster(int userId, String username) {
        super(userId, username);
        this.campaigns = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
    }
    
    /**
     * Erstellt eine neue Kampagne und fügt sie zur Liste der Kampagnen des Spielleiters hinzu
     * 
     * @param campaign Die zu erstellende Kampagne
     */
    public void createCampaign(Campaign campaign) {
        campaign.setGameMaster(this);
        campaigns.add(campaign);
    }
    /**
     * Erstellt eine neue Gruppe und setzt den Spielleiter als Ersteller
     * 
     * @param group Die zu erstellende Gruppe
     */
    public void createGroup(Group group) {
        group.setGameMaster(this);
    }
    /**
     * Erstellt ein neues Monster und fügt es zur Liste der Monster des Spielleiters hinzu
     * 
     * @param monster Das zu erstellende Monster
     */
    public void createMonster(Monster monster) {
        monsters.add(monster);
    }
    /**
     * Erstellt einen neuen Gegenstand und fügt ihn zur Liste der Gegenstände des Spielleiters hinzu
     * 
     * @param item Der zu erstellende Gegenstand
     */
    public void createItem(Item item) {
        items.add(item);
    }
    /**
     * Gibt die Liste aller Kampagnen des Spielleiters zurück
     * 
     * @return Liste der Kampagnen
     */
    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }
    /**
     * Gibt die Liste aller Monster des Spielleiters zurück
     * 
     * @return Liste der Monster
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
    /**
     * Gibt die Liste aller Gegenstände des Spielleiters zurück
     * 
     * @return Liste der Gegenstände
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    /**
     * Setzt die Liste der Kampagnen des Spielleiters
     * 
     * @param campaigns Die neue Liste der Kampagnen
     */
    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
    /**
     * Setzt die Liste der Monster des Spielleiters
     * 
     * @param monsters Die neue Liste der Monster
     */
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
    /**
     * Setzt die Liste der Gegenstände des Spielleiters
     * 
     * @param items Die neue Liste der Gegenstände
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "GameMaster{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", campaigns=" + campaigns.size() +
                ", monsters=" + monsters.size() +
                ", items=" + items.size() +
                '}';
    }
} 
 