package src.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.StaticMethods;
import src.VariablesForMultipleClasses;

public class SessionViewPlayer extends SessionView {
    /** Label which greets the user */
    Label greetuserLabel;
    /** Button which logs out the user and returns to the login */
    Button logoutButton;

    HBox bottomLayout;
    VBox centerLayout;
    VBox leftMenus;

    /**
     * 
     */
    public SessionViewPlayer() {
        leftMenus = new VBox(0.1);
        bottomLayout = new HBox();
        centerLayout = new VBox();

        leftMenus.setPrefWidth(200);

        this.greetuserLabel = new Label();
        greetuserLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px");
        this.logoutButton = new Button("Abmelden");

        Label label = new Label("Übersicht");
        label.setStyle(VariablesForMultipleClasses.WHITE_TEXT_COLOR);
        leftMenus.getChildren().add(label);

        centerLayout.getChildren().addAll(greetuserLabel);
        bottomLayout.getChildren().addAll(logoutButton);

        super.setLeft(leftMenus);
        super.setBottom(bottomLayout);
        super.setCenter(centerLayout);

        super.setStyle(StaticMethods.BLACK_BACKGROUND_CSS);
    }

}