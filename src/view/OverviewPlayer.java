package src.view;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import src.VariablesForMultipleClasses;

public class OverviewPlayer extends VBox {
    Label title;


    public OverviewPlayer() {
        title = new Label();
        super.getChildren().add(title);
        super.setStyle(VariablesForMultipleClasses.BLACK_BACKGROUND_CSS);
    }
}
