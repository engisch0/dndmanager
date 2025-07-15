package src.model;

/**
 * 
 * Diese Klasse speichert alle Benutzerdaten die in der Datenbank gespeichert sind.
 * Basisklasse für alle User, alle Arten von User (Player sowie GameMaster erben von dieser Klasse).
 * @since M1
 */
public abstract class User {
    /** ID der Benutzer */
    private int userId;
    /** Benutzername */
    private String username;

    /**
     * Konstruktor, welche eine User initialisiert
     * @param userId ID der User
     * @param username Benutzername der User
     */
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * Gibt das UserID zurück
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Ändert das UserID.
     * @param userId das UserID, das das alte ersetzen soll
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gibt das Username zurück.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Ändert das Username.
     * @param username das username, das das alte ersetzen soll
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
