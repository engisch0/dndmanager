package src.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import src.VariablesForMultipleClasses;
import src.model.Character;
import src.model.GameMaster;
import src.model.Player;
import src.model.User;

/**
 * Klasse die das Datenbank kontrolliert, d.h. es kann Einträge sehen, bearbeiten und löschen.
 * @since M1
 */
public final class DatabaseController {
    /** Variable, die für die Verbindung mit der Datenbank verantwortlich ist. */
    Connection databaseConnection;

    /**
     * Konstruktor: Initialisiert ein Objekt von Typ DatabaseController und versucht mit der Datenbank zu verbinden.
     */
    public DatabaseController() {
        databaseConnection = connect(3307, "projekt_praktikumsersatz", "root", null);
    }

    /**
     * Verbindet mit der Datenbank und gibt eine Verbindung zurück.
     *
     * @param port Portnummer von MySQL/MariaDB
     * @param dbname Name der Datenbank
     * @param user Benutzer in der Datenbank (nicht von der Spiel)
     * @param pw Passwort von der Benutzer (der Benutze von der Datenbank)
     * @return Eine Verbindung mit der Datenbank
     * 
     * @throws SQLException
     * Wenn die Verbindung fehlschlägt, beispielsweise wenn das Datenbanksystem nicht gestartet ist oder falsche Daten in der Parameter eingegeben worden sind.
     * 
     * @throws RuntimeException Als Folge des {@code SQLException}, die App wird dann nicht gestartet und stürzt ab.
     */
    public Connection connect(int port, String dbname, String user, String pw) {
        Connection connection;
        String url = "jdbc:mysql://localhost:" + port + "/" + dbname;
        try {
            connection = DriverManager.getConnection(url, user, pw);
            System.out.println("Verbindung erfolgreich hergestellt.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden: " + e.getMessage() + ".");
            System.out.println("Detailliertes Fehlermeldung:");
            e.printStackTrace();
            throw new RuntimeException("Die App kann aufgrund eines fehlgeschlagenen Verbindungsversuch in das Datenbank nicht fortsetzen. Die App wird beendet.");
        }
    }

    /**
     * Meldet einen Benutzer an.
     *  
     * @return true wenn der Benutzer sich automatisch angemeldet hat, ansonsten immer false.
     */
    public boolean userLogin(String username, String password) {
        try {
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT username FROM t_user WHERE username = \"" + username + "\";");

            // Suche des Benutzername //
            ResultSet rs = statement.executeQuery();

            boolean userNameFound = false;
            while (rs.next()) {
                String fetchedusername = rs.getString(1);
                System.out.println("Abgerufene Benutzername: " + fetchedusername);
                //Speichert das Benutzername
                if (fetchedusername.equals(username)) {
                    userNameFound = true;
                    break;
                }
            }

            if (!userNameFound) {
                System.out.println("Anmeldung fehlgeschlagen.");
                return false;       
            }

            //Herausfinden des IDs
            statement = databaseConnection.prepareStatement("SELECT userID FROM t_user WHERE username = \"" + username + "\";");
            rs = statement.executeQuery();

            int fetcheduserid = -1;
            while (rs.next()) {
                fetcheduserid = rs.getInt(1);
            }

            // Passwortüberprüfung //
            statement = databaseConnection.prepareStatement("SELECT t_user.password FROM t_user WHERE t_user.username = \"" + username  + "\";");
            rs = statement.executeQuery();

            while (rs.next()) {
                // Überprüft, ob dass Passwort mit dem eingegebenen Passwort übereinstimmt
                String fetchedpassword = rs.getString(1);
                System.out.println("Abgerufene Passwort: " + fetchedpassword);
                if (fetchedpassword.equals(password)) {
                    System.out.println("Anmeldung erfolgreich.");
                    int whichtypeOfPlayer = checkIfUserIsPlayerOrGameMaster(username);

                    if (whichtypeOfPlayer == 1) {
                        System.out.println("Sie sind ein Spieler!");
                    } else {
                        System.out.println("Sie sind ein Spielleiter!");
                    }
                        VariablesForMultipleClasses.currentloggedinID = fetcheduserid;
                        return true; //Für das GUI, damit es Bescheid weiß dass die Anmeldung erfolgreich ist
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Anmeldung fehlgeschlagen.");
        return false;
    }

    /**
     * Überprüft ob ein Benutzer ein Spieler oder ein Spielleiter ist.
     * @param username der Benutzername
     * @return 1 wenn ein Spieler, 2 wenn ein Spielleiter und -1 wenn ein Fehler aufgetreten ist (unter anderem wenn kein Benutzer mit dem Namen in der Parameter gefunden worden ist)
     */
    private int checkIfUserIsPlayerOrGameMaster(String username) {
        try (CallableStatement statement = databaseConnection.prepareCall("{? = call f_checkIfUserIsPlayerOrGameMaster(?)}")) {
    
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, username);  
    
            statement.execute();
            
            return statement.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Meldet einen Nutzer ab.
     */
    @Deprecated
    public void userLogout() {
        // Abmeldung von Nutzer funktioniert bereits.
    }

    /**
     * Erstellt ein neue Konto für ein Benutzername, indem die Daten in der Datenbank hinzugefügt werden.
     */
    public boolean createUser(String username, String password) {
        try (Statement statement = databaseConnection.createStatement()) {
            statement.executeUpdate("INSERT INTO t_user (username, password, last_login, created_at) VALUES (\"" + username + "\", \"" + password + "\", current_timestamp(), current_timestamp());");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Ladet die Benutzerdaten von der Datenbank, erstellt ein neues Objekt mit den Daten der jeweiligen Nutzer und fügt diese abschließend in eine Liste hinzu.
     * @return Users, eine Liste mit Benutzerdaten
     */
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // 1. Der Benutzer wird aufgerufen
            String query = "SELECT t_user.userID, t_user.username FROM t_user;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("t_user.username");

                System.out.println(username);
                if (checkIfUserIsPlayerOrGameMaster(username) == 1) {
                    users.add(new Player(userID, username));
                    //Einstellungen und Spieldaten werden geladen
                    Player player = (Player) users.getLast();

                        // Zuerst die Charaktere
                    String characterName = "", characterRace = "";
                    int characterID = -1, characterAge = 0;
                    double characterSize = 0.00;

                    String characterQuery = "SELECT t_character.* FROM t_character JOIN t_ownerofcharacter ON t_ownerofcharacter.characterID = t_character.characterID JOIN t_player ON t_player.playerID = t_ownerofcharacter.playerID WHERE t_ownerofcharacter.playerID = " + player.getUserId() + ";";
                    try {
                        PreparedStatement characterPreparedStatement = databaseConnection.prepareStatement(characterQuery);
                        ResultSet characterResultSet = characterPreparedStatement.executeQuery();

                        while (characterResultSet.next()) {
                            characterID = characterResultSet.getInt("t_character.characterID");
                            characterName = characterResultSet.getString("t_character.name");
                            characterRace = characterResultSet.getString("t_character.race");
                            characterAge = characterResultSet.getInt("t_character.Age");
                            characterSize = characterResultSet.getDouble("t_character.size");
                            // Charaktere mit ID 0 gibt es nicht, deswegen kann es als Überprüfung mitgenommen werden
                            if (characterID == -1) {
                                continue;
                            } else {
                                player.characters.add(new Character(characterID, characterName, characterRace, characterAge, characterSize));
                            }
                            
                        }
                        users.set(users.size() - 1, player);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Spätere Daten und Einstellungen folgen. //
                } else {
                    users.add(new GameMaster(userID, username));
                    // Hier folgt Später die Ladung des Daten von der Spielleiter.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return users;
    }
}
