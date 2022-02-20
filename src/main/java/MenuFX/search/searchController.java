package MenuFX.search;

import Tools.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class searchController implements Initializable {
    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();
    // TextField to input word to search
    @FXML
    TextField word;
    // Should show all results from each database
    // Use a scroll wheel to be able to view all inputs
    // IF possible, add a Like button and sort it by likes (descending)

    // Show the current user's definition if they have the word on their database
    // If not show "This word has not been defined by the user yet"

    // If the word is not mentioned in the database INPUT word has not been added by anyone yet.

    // This is by far the hardest one to implement.

    @FXML
    Label error, userDefinition, otherDefinitions;

    @FXML
    Button search, back, like;
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
