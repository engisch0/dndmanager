package src;

public final class VariablesForMultipleClasses {
    
    /** Temporäre Speicherung der Nutzername des derzeit angemeldeten Nutzer */
    public static transient String currentloggedinusername = "";

    /** Stellt die Textfarbe auf Weiß */
    public static final String WHITE_TEXT_COLOR = "-fx-text-fill: white";

    /** Stellt den Hintegrund von einem belibigen node ein, indem das "-fx-background-color" auf black eingestellt wird */
    public static final String BLACK_BACKGROUND_CSS = "-fx-background-color: black";

    /** Klasse darf nicht initialisiert werden */
    private VariablesForMultipleClasses(){}
}
