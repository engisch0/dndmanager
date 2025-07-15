package src;

import javafx.scene.paint.Color;

public final class StaticMethods {
    
    /** Stellt den Hintegrund von einem belibigen node ein, indem das "-fx-background-color" auf black eingestellt wird */
    public static final String BLACK_BACKGROUND_CSS = "-fx-background-color: black";

    /** Erlaube niemanden diese Klasse zu initialisieren*/
    private StaticMethods() {}

    /**
     * Gibt das RGB-Wert von einerm Farbe zurück.
     * @param color
     * @return
     */
    public static String toRgbString(Color color) {
        return String.format("rgb(%d, %d, %d)", 
            (int)(color.getRed() * 255), 
            (int)(color.getGreen() * 255), 
            (int)(color.getBlue() * 255));
    }
}
