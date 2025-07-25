package src.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import src.VariablesForMultipleClasses;

/**
 * This class contains the login components
 * Diese Klasse beinhaltet die Loginkomponenten.
 * 
 * @since M1
 */
public class LoginView extends VBox {
    /** Label, das den Benutzer begrüßt */
    Label welcomeLabel;
    /** Label, die Informationen an den Nutzer weitergibt */
    Label informationsLabel;
    /** Label für den usernameField-Variable */
    Label usernameFieldLabel;
    /** Label für den passwordField-Variable */
    Label passwordFieldLabel;

    /** Eingabe für den Nutzername */
    TextField usernameField;
    /** Eingabe für das Passwort */
    PasswordField passwordField;

    /** Variable für den horizontalen Ausrichtung der Buttons */
    HBox buttons;

    /** Button, welches das App beendet */
    Button exitButton;
    /** Button, welche zur RegistrationView weiterleitet*/
    Button createAccountButton;
    /** Button, welche eine Anmeldeversuch durchführt */
    Button loginButton;

    /**
     * Constructor, which initializes a new Object with the type LoginView and then goes to the createLoginScene-method.
     * Konstruktor, welche ein neues Objekt von Typ LoginView erstellt und dann zur createLoginScene-Methode rübergeht.
     */
    protected LoginView() {
        super();
        createLoginScene();
    }

    /**
     * This methods creates Object/Components for the LoginView
     * Diese Methode erstellt Objekte/Komponenten für das LoginView
     */
    public void createLoginScene() {
        super.setStyle(VariablesForMultipleClasses.BLACK_BACKGROUND_CSS);
        
        // Initialisierung der Objekte und deren Anpassung
        welcomeLabel = new Label("Willkommen zur Dungeons and Dragons");
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px");
        informationsLabel = new Label("Bitte melden Sie sich an. Sie können ebenfalls ein Konto erstellen falls Sie noch keins haben.");
        informationsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px");

        usernameFieldLabel = new Label("Nutzername");
        usernameFieldLabel.setTextFill(Color.WHITE);
        usernameField = new TextField();
        passwordFieldLabel = new Label("Passwort (nicht sichtbar)");
        passwordFieldLabel.setTextFill(Color.WHITE);

        passwordField = new PasswordField();

        
        buttons = new HBox(10);
        loginButton = new Button("Anmelden");
        loginButton.setStyle("-fx-text-fill: white; -fx-background-color: rgb(132, 160, 211);"/*
                                                                                                  * -fx-padding: 200px
                                                                                                  * 200px 200px 200px;
                                                                                                  * -fx-font-size: 50px"
                                                                                                  */);

        createAccountButton = new Button("Konto erstellen");
        createAccountButton.setStyle("-fx-text-fill: white; -fx-background-color: rgb(132, 160, 211);"/*
                                                                                                        * -fx-padding:
                                                                                                        * 200px 200px
                                                                                                        * 200px 200px;
                                                                                                        * -fx-font-size:
                                                                                                        * 50px"
                                                                                                        */);

        exitButton = new Button("Beenden");
        exitButton.setStyle("-fx-text-fill: white; -fx-background-color: rgb(132, 160, 211);"/*
                                                                                                 * -fx-padding: 200px
                                                                                                 * 200px 200 zpx 200px;
                                                                                                 * -fx-font-size: 50px"
                                                                                                 */);
        exitButton.setOnAction(e -> {
            System.exit(1234);
        });

        buttons.getChildren().addAll(loginButton, createAccountButton, exitButton);

        super.getChildren().addAll(welcomeLabel, informationsLabel, usernameFieldLabel, usernameField, passwordFieldLabel, passwordField, buttons);
    }
}
        
