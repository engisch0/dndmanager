package src.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Klasse für den RegistrationView
 * 
 * Erbt VBox.
 */
public class RegistrationView extends VBox {

    /** Eingabe für das Nutzername */
    TextField usernameField;
    /** Eingabe für das Passwort */
    PasswordField passwordField;
    /** Label für den Titel */
    Label createuserHeadline;
    /** Label für den userNameField-Variable */
    Label usernameLabel;
    /** Label für den passwordField-Variable */
    Label passwordLabel;

    /** Button, welche den Nutzer zur den Login zurückbringt */
    Button returntologinButton;
    /** Button, welche eine Registrierungsversuch durchführt */
    Button createButton;

    /**
     * Konstrutkor, welche eine neue Objekt diesen Types erzeugt und dann zur der Methode createRegistrationView rübergeht.
     */
    protected RegistrationView() {
        createRegistrationView();
    }

    /**
     * Erstellt die Komponenten, die für die Registrierung erforderlich sind und fügt diese in dieser Klasse (zur der VBox) hinzu
     */
    public void createRegistrationView() {
        super.setStyle("-fx-background-color: black");

        usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        createuserHeadline = new Label("Konto erstellen");
        createuserHeadline.setStyle("-fx-text-fill: white; -fx-font-size: 20px");

        usernameLabel = new Label(
                "Bitte geben Sie hier Ihr neue Benutzername ein.\n Bitte beachten Sie, dass das Benutzername nicht bereits vergeben worden sein darf.");
        passwordLabel = new Label(
                "Bitte geben Sie nun ein Passwort ein, die Sie für das Login benötigen werden.");
        usernameLabel.setStyle("-fx-text-fill: white;");
        passwordLabel.setStyle("-fx-text-fill: white;");

        returntologinButton = new Button("Zurück zur Login");

        createButton = new Button("Benutzer erstellen");
        createButton.setOnAction(e -> {
            if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()
                    || passwordField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Erstellung des Benutzers fehlgeschlagen.");
                alert.setTitle("Benutzererstellung fehlgeschlagen");
                alert.setHeaderText("");
                alert.show();
                return;
            }
            createButton.setDisable(true);
        });

        super.getChildren().addAll(createuserHeadline, usernameLabel, usernameField,
                passwordLabel, passwordField, returntologinButton, createButton);
    }
}
