package src.model;

import java.util.ArrayList;

/**
 * Klasse speziell für den Player. Vererbung von User
 * 
 * @since M1
 * 
 * @see src.model.User
 */
public class Player extends User {
    /** Liste von Charakteren die der Player besitzt */
    public ArrayList<Character> characters;
    
    /**
     * Konstruktor der Klasse Player
     * @param userID
     * @param username
     */
    public Player(int userID, String username) {
        super(userID, username);
        this.characters = new ArrayList<>();
    }
}