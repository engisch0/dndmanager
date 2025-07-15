package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert eine Gruppe von Charakteren im RPG-Manager.
 * Eine Gruppe wird von einem Spielleiter verwaltet und kann an Kampagnen
 * teilnehmen.
 * 
 * @since M2
 */
public class Group {
    private String groupId;
    private String name;
    private List<Character> members;
    private GameMaster gameMaster;

    /**
     * Standardkonstruktor
     */
    public Group() {
        this.members = new ArrayList<>();
    }

    /**
     * Konstruktor mit grundlegenden Attributen
     *
     * @param groupId    Eindeutige Gruppen-ID
     * @param name       Name der Gruppe
     * @param gameMaster Spielleiter, der die Gruppe verwaltet
     */
    public Group(String groupId, String name, GameMaster gameMaster) {
        this.groupId = groupId;
        this.name = name;
        this.gameMaster = gameMaster;
        this.members = new ArrayList<>();
    }

    /**
     * Fügt einen Charakter zur Gruppe hinzu
     *
     * @param character Der hinzuzufügende Charakter
     */
    public void addCharacter(Character character) {
        members.add(character);
    }

    /**
     * Entfernt einen Charakter aus der Gruppe
     *
     * @param character Der zu entfernende Charakter
     */
    public void removeCharacter(Character character) {
        members.remove(character);
    }

    /**
     * Gibt die Liste aller Mitglieder der Gruppe zurück
     *
     * @return Liste der Charaktere in der Gruppe
     */
    public List<Character> getMembers() {
        return members;
    }

    /**
     * Gibt den Spielleiter zurück, der die Gruppe verwaltet
     *
     * @return Spielleiter der Gruppe
     */
    public GameMaster getGameMaster() {
        return gameMaster;
    }

    // Getter und Setter
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Character> members) {
        this.members = members;
    }

    public void setGameMaster(GameMaster gameMaster) {
        this.gameMaster = gameMaster;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members.size() +
                ", gameMaster=" + (gameMaster != null ? gameMaster.getUsername() : "none") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Group group = (Group) o;
        return groupId != null ? groupId.equals(group.groupId) : group.groupId == null;
    }

    @Override
    public int hashCode() {
        return groupId != null ? groupId.hashCode() : 0;
    }
}
