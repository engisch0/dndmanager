package src.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.VariablesForMultipleClasses;
import src.controller.DatabaseController;
import src.controller.DnDManager;
import src.model.GameMaster;
import src.model.Player;

/**
 * Main-Klasse, welche alle Instanzen von Klassen beinhaltet die erforderlich sind. Es ist ebenfalls ein Einstiegspunkt für diese App.
 * 
 * @since M1
 */
public final class App extends Application {
    /** Variable, die den Datenbank verwaltet */
    DatabaseController databaseController = new DatabaseController();
    DnDManager dnDManager = new DnDManager();
    SessionView sessionView;

    /** Der primäre Stage */
    Stage primaryStage;
    /** Der primäre Scene */
    Scene primaryScene;

    /** Das View für das Login */
    LoginView loginView;

    /** Das View für die Registrierung*/
    RegistrationView registrationView;

    /**
     * Diese Methode initialisiert die primaryStage, stellt das Scene und das loginView als Standart ein und zeigt dann das primaryStage an (d.h der Spieler wird mit der Anmeldung begrüßt)
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryScene = new Scene(new VBox(), 1440, 900);
        registrationView = new RegistrationView();
        //sessionView = new SessionView();

        dnDManager.users = databaseController.loadUsers();

        // Dinge für das loginView
        loginView = new LoginView();
        loginView.exitButton.setOnAction(e -> {
            exit();
        });

        loginView.loginButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(
                    "Die Anmeldung ist fehlgeschlagen. Sie haben möglicherweise die Anmeldedaten falsch eingegeben oder vertippt. Bitte versuchen Sie es erneut.");
            alert.setTitle("Anmeldung fehlgeschlagen");
            alert.setHeaderText("");
            
             
            if (!databaseController.userLogin(loginView.usernameField.getText(), loginView.passwordField.getText())) {
                alert.show();
            } else {
                loginView.usernameField.clear();
                loginView.passwordField.clear();
                // DEPRECATED: getCurrentUserInformationAndPutInTheCurrentUserVariable();
                loadDnDSession();
            }
            
        });

        loginView.createAccountButton.setOnAction(e -> {
            primaryScene.setRoot(registrationView);
        });


        // Dinge für das RegistrationView //
        registrationView.returntologinButton.setOnAction(e -> {
            primaryScene.setRoot(loginView);
        });

        registrationView.createButton.setOnAction(e -> {
            if (registrationView.usernameField.getText().isBlank() || registrationView.usernameField.getText().isEmpty()
                    || registrationView.passwordField.getText().isEmpty() || registrationView.passwordField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Erstellung des Benutzers fehlgeschlagen.");
                alert.setTitle("Benutzererstellung fehlgeschlagen");
                alert.setHeaderText("");
                alert.show();
                return;
            }
            
            databaseController.createUser(registrationView.usernameField.getText(), registrationView.passwordField.getText());
            registrationView.createButton.setDisable(true);
        });

        primaryScene.setRoot(loginView);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("DnD Manager: Login");
        primaryStage.show();
    }

    public void exit() {
        primaryStage.close();
        System.exit(0);
    }

    /**
     * Ladet den DnD-Session, nachdem der Benutzer sich automatisch angemeldet hat
     */
    private void loadDnDSession() {
        if (dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1) instanceof Player) {
            Player currentPlayer = (Player) dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1);
            SessionViewPlayer sessionViewPlayer = new SessionViewPlayer();

            sessionViewPlayer.greetuserLabel.setText("Willkommen Spieler, " + dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1).getUsername() + "!");

            // Dinge für das SessionViewPlayer
            sessionViewPlayer.logoutButton.setOnAction(e -> {
                primaryScene.setRoot(loginView);
                primaryStage.setTitle("DnD Manager: Login");
                VariablesForMultipleClasses.currentloggedinID = -1;
                sessionViewPlayer.greetuserLabel.setText(null);

                // Dinge für das OverviewPlayer
                sessionViewPlayer.overview.title.setText("Benutzerinformationen:\nAnzahl Charaktere: " + currentPlayer.characters.size() + "\n");
                sessionViewPlayer.overview.title.setStyle(VariablesForMultipleClasses.WHITE_TEXT_COLOR);

                for (int i = 0; i < currentPlayer.characters.size(); i++) {
                    sessionViewPlayer.characterview.characternames.add(currentPlayer.characters.get(i).getName());
                }

                

                //databaseController.userLogout();
            });

            for(int i = 0; i < ((Player) dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1)).characters.size(); i++) {
                sessionViewPlayer.characterview.characternames.add(((Player) dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1)).characters.get(i).getName());
            }

            this.sessionView = sessionViewPlayer;
        } else if(dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1) instanceof GameMaster) {
            this.sessionView = new SessionViewGameMaster();

            ((SessionViewGameMaster) sessionView).greetuserLabel.setText("Willkommen Spielleiter, " + dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1).getUsername() + "!");

            // Dinge für das SessionViewgameMaster
            ((SessionViewGameMaster) sessionView).logoutButton.setOnAction(e -> {
                primaryScene.setRoot(loginView);
                primaryStage.setTitle("DnD Manager: Login");
                VariablesForMultipleClasses.currentloggedinID = -1;
                ((SessionViewGameMaster) sessionView).greetuserLabel.setText(null);
                //databaseController.userLogout();
            });
        }

        
        primaryScene.setRoot(sessionView);
        primaryStage.setTitle("DnD Session; Als " + dnDManager.users.get(VariablesForMultipleClasses.currentloggedinID - 1).getUsername() + " angemeldet");
    }

    /**
     * Main-Methode
     */
    public static void main(String[] args) {
        launch(args);
    }
}