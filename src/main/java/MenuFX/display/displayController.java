package MenuFX.display;

import Tools.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class displayController implements Initializable {
    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();
    // TextField to input word to search
    @FXML
    TextField word;
    // Should show all words from this databse

    @FXML
    ToggleGroup databaseWordsTG;

    @FXML
    RadioButton userWords, databaseWords;

    @FXML
    Label error, userDefinition, otherDefinitions;

    @FXML
    Button moveToSearch, back, like;
    // Buttons to complete process


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
//        back.setOnAction(e -> setBack(e));
    }

    private void setSearch(){
        //TODO: Check database if word exists. If yes, edit. If no, display error message in userDefinition Label
        //      Set otherDefinitions to show in the scrollpane
    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"menu","Menu");
    }
}
