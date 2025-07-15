 
package src.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Repräsentiert ein Monster im RPG-Manager.
* Monster werden vom Spielleiter erstellt und verwaltet.
*/
public class Monster {
   private String monsterId;
   private String name;
   private String type;
   private int challengeRating;
   private int hitPoints;
   private int armorClass;
   private Map<String, Integer> attributes;
   private List<Attack> attacks;
   /**
    * Standardkonstruktor
    */
   public Monster() {
       this.attributes = new HashMap<>();
       this.attacks = new ArrayList<>();
   }
   /**
    * Konstruktor mit grundlegenden Attributen
    *
    * @param monsterId Eindeutige Monster-ID
    * @param name Name des Monsters
    * @param type Typ des Monsters
    */
   public Monster(String monsterId, String name, String type) {
       this.monsterId = monsterId;
       this.name = name;
       this.type = type;
       this.attributes = new HashMap<>();
       this.attacks = new ArrayList<>();
   }
   /**
    * Konstruktor mit allen Attributen
    *
    * @param monsterId Eindeutige Monster-ID
    * @param name Name des Monsters
    * @param type Typ des Monsters
    * @param challengeRating Herausforderungsgrad des Monsters
    * @param hitPoints Lebenspunkte des Monsters
    * @param armorClass Rüstungsklasse des Monsters
    */
   public Monster(String monsterId, String name, String type, int challengeRating, int hitPoints, int armorClass) {
       this.monsterId = monsterId;
       this.name = name;
       this.type = type;
       this.challengeRating = challengeRating;
       this.hitPoints = hitPoints;
       this.armorClass = armorClass;
       this.attributes = new HashMap<>();
       this.attacks = new ArrayList<>();
   }
   /**
    * Gibt die Attribute des Monsters zurück
    *
    * @return Map mit Attributnamen und -werten
    */
   public Map<String, Integer> getAttributes() {
       return attributes;
   }
   /**
    * Gibt die Angriffe des Monsters zurück
    *
    * @return Liste der Angriffe
    */
   public List<Attack> getAttacks() {
       return attacks;
   }
   /**
    * Fügt einen Angriff zum Monster hinzu
    *
    * @param attack Der hinzuzufügende Angriff
    */
   public void addAttack(Attack attack) {
       attacks.add(attack);
   }
   /**
    * Entfernt einen Angriff vom Monster
    *
    * @param attack Der zu entfernende Angriff
    */
   public void removeAttack(Attack attack) {
       attacks.remove(attack);
   }
   // Getter und Setter
   public String getMonsterId() {
       return monsterId;
   }
   public void setMonsterId(String monsterId) {
       this.monsterId = monsterId;
   }
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public String getType() {
       return type;
   }
   public void setType(String type) {
       this.type = type;
   }
   public int getChallengeRating() {
       return challengeRating;
   }
   public void setChallengeRating(int challengeRating) {
       this.challengeRating = challengeRating;
   }
   public int getHitPoints() {
       return hitPoints;
   }
   public void setHitPoints(int hitPoints) {
       this.hitPoints = hitPoints;
   }
   public int getArmorClass() {
       return armorClass;
   }
   public void setArmorClass(int armorClass) {
       this.armorClass = armorClass;
   }
   public void setAttributes(Map<String, Integer> attributes) {
       this.attributes = attributes;
   }
   public void setAttacks(List<Attack> attacks) {
       this.attacks = attacks;
   }
   @Override
   public String toString() {
       return "Monster{" +
               "monsterId='" + monsterId + '\'' +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", challengeRating=" + challengeRating +
               ", hitPoints=" + hitPoints +
               ", armorClass=" + armorClass +
               ", attacks=" + attacks.size() +
               '}';
   }
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Monster monster = (Monster) o;
       return monsterId != null ? monsterId.equals(monster.monsterId) : monster.monsterId == null;
   }
   @Override
   public int hashCode() {
       return monsterId != null ? monsterId.hashCode() : 0;
   }
}
 