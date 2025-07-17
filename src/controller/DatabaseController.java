package src.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import src.VariablesForMultipleClasses;
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
        try (Statement statement = databaseConnection.createStatement()) {
            // Suche des Benutzername //
            ResultSet rs = statement.executeQuery("SELECT username FROM t_user WHERE username = \"" + username + "\"");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            boolean userNameFound = false;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    //Speichert das BBenutzername
                    if (username.equals(rs.getString(i))) {
                        userNameFound = true;
                        break;
                    }
                }
                
            }

            if (!userNameFound) {
                System.out.println("Anmeldung fehlgeschlagen.");
                return false;       
            }

            // Passwortüberprüfung //
            rs = statement.executeQuery("SELECT t_user.password FROM t_user WHERE t_user.username = \"" + username  + "\"");
            metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    // Überprüft, ob dass Passwort mit dem eingegebenen Passwort übereinstimmt
                    if (rs.getString(i).equals(password)) {
                        System.out.println("Anmeldung erfolgreich.");
                        int whichtypeOfPlayer = checkIfUserIsPlayerOrGameMaster(username);

                        if (whichtypeOfPlayer == 1) {
                            System.out.println("Sie sind ein Spieler!");
                        } else {
                            System.out.println("Sie sind ein Spielleiter!");
                        }
                        VariablesForMultipleClasses.currentloggedinusername = username;
                        return true; //Für das GUI, damit es Bescheid weiß dass die Anmeldung erfolgreich ist
                    }
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
                } else {
                    users.add(new GameMaster(userID, username));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return users;
    }

    public ArrayList<Character> loadCharacterOfUser(String username) {
        return null;
    }
}
