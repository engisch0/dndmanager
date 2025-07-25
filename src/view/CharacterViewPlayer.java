package src.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.VariablesForMultipleClasses;

public class CharacterViewPlayer extends HBox {
    
    ListView<String> listOfCharacterNames;
    VBox characterInformation;
    Label characterInformationsText;
    ArrayList<String> characternames;

    public CharacterViewPlayer() {
        this.listOfCharacterNames = new ListView<>();
        characterInformation = new VBox(1.0);
        characterInformationsText = new Label("Informationen über Character: \n\n Name: \n\n Rasse: \n\n Alter: \n\n Größe: ");
        characterInformationsText.setStyle(VariablesForMultipleClasses.WHITE_TEXT_COLOR);
        characterInformation.getChildren().addAll(characterInformationsText);

        this.characternames = new ArrayList<>();

        listOfCharacterNames = new ListView<>();

        

        

        super.getChildren().addAll(listOfCharacterNames, characterInformation);
    }
}
