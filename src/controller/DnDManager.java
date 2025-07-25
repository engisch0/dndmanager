package src.controller;


import java.util.ArrayList;

import src.model.User;

/**
 * Diese Klasse verwaltet die DnD-Session.
 * 
 * @since M1
 */
public class DnDManager {
    /** Liste aller Benutzer die in der App registriert sind, diese ArrayList kann die Klassen Player sowie GameMaster speichern */    
    public ArrayList<User> users;

    /**
     * Konstruktor der DnDManager-Klasse
     */
    public DnDManager() {
        this.users = new ArrayList<>(); //Initialisierung erfolgt über App.java
    }
}