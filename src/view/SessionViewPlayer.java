package src.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.VariablesForMultipleClasses;

public class SessionViewPlayer extends SessionView {
    /** Label which greets the user */
    Label greetuserLabel;
    /** Button which logs out the user and returns to the login */
    Button logoutButton;

    HBox bottomLayout;
    VBox topLayout;
    VBox leftMenus;

    CharacterViewPlayer characterview;
    OverviewPlayer overview;
    
    boolean firstInitiated = true;

    // Komponente für die LeftMenu
    Button overviewButton;
    Button characterViewButton;

    /**
     * 
     */
    public SessionViewPlayer() {
        leftMenus = new VBox(0.1);
        bottomLayout = new HBox();
        topLayout = new VBox();
        characterview = new CharacterViewPlayer();
        this.overview = new OverviewPlayer();
        this.overviewButton = new Button("Übersicht");
        this.characterViewButton = new Button("Charaktere");
        this.characterViewButton.setOnAction(e -> {
            super.setCenter(characterview);
            greetuserLabel.setText("Charaktere");
        });
        this.overviewButton.setOnAction(e -> {
            super.setCenter(overview);
            greetuserLabel.setText("Übersicht");
        });

        super.setCenter(overview);

        leftMenus.setPrefWidth(200);

        this.greetuserLabel = new Label();
        greetuserLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px");
        this.logoutButton = new Button("Abmelden");
        
        leftMenus.getChildren().addAll(overviewButton, characterViewButton);

        topLayout.getChildren().addAll(greetuserLabel);
        bottomLayout.getChildren().addAll(logoutButton);

        super.setLeft(leftMenus);
        super.setBottom(bottomLayout);
        super.setTop(topLayout);

        super.setStyle(VariablesForMultipleClasses.BLACK_BACKGROUND_CSS);
    }

    public void loadCharacterView() {
        super.setCenter(overview);
    }
}