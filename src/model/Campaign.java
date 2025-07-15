
package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert eine Kampagne im RPG-Manager.
 * Eine Kampagne wird von einem Spielleiter erstellt und kann mehrere
 * teilnehmende Gruppen haben.
 */
public class Campaign {
    private String campaignId;
    private String name;
    private String description;
    private GameMaster gameMaster;
    private List<Group> participatingGroups;

    /**
     * Standardkonstruktor
     */
    public Campaign() {
        this.participatingGroups = new ArrayList<>();
    }

    /**
     * Konstruktor mit grundlegenden Attributen
     * 
     * @param campaignId  Eindeutige Kampagnen-ID
     * @param name        Name der Kampagne
     * @param description Beschreibung der Kampagne
     * @param gameMaster  Spielleiter, der die Kampagne erstellt hat
     */
    public Campaign(String campaignId, String name, String description, GameMaster gameMaster) {
        this.campaignId = campaignId;
        this.name = name;
        this.description = description;
        this.gameMaster = gameMaster;
        this.participatingGroups = new ArrayList<>();
    }

    /**
     * Fügt eine Gruppe zur Kampagne hinzu
     * 
     * @param group Die hinzuzufügende Gruppe
     */
    public void addGroup(Group group) {
        participatingGroups.add(group);
    }

    /**
     * Entfernt eine Gruppe aus der Kampagne
     * 
     * @param group Die zu entfernende Gruppe
     */
    public void removeGroup(Group group) {
        participatingGroups.remove(group);
    }

    /**
     * Gibt die Liste aller teilnehmenden Gruppen zurück
     * 
     * @return Liste der Gruppen in der Kampagne
     */
    public List<Group> getGroups() {
        return participatingGroups;
    }

    /**
     * Gibt den Spielleiter zurück, der die Kampagne erstellt hat
     * 
     * @return Spielleiter der Kampagne
     */
    public GameMaster getGameMaster() {
        return gameMaster;
    }

    // Getter und Setter
    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
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

    public void setGameMaster(GameMaster gameMaster) {
        this.gameMaster = gameMaster;
    }

    public void setParticipatingGroups(List<Group> participatingGroups) {
        this.participatingGroups = participatingGroups;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId='" + campaignId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gameMaster=" + (gameMaster != null ? gameMaster.getUsername() : "none") +
                ", participatingGroups=" + participatingGroups.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Campaign campaign = (Campaign) o;
        return campaignId != null ? campaignId.equals(campaign.campaignId) : campaign.campaignId == null;
    }

    @Override
    public int hashCode() {
        return campaignId != null ? campaignId.hashCode() : 0;
    }
}
