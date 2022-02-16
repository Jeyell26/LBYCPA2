package MenuFX.delete;

import Tools.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class deleteController implements Initializable {
    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();
    // TextField to input word and definition
    // Should add to existing Database

    @FXML
    TextField word;

    @FXML
    Label error;

    @FXML
    Button delete, back;
    // Buttons to complete process

    // BST bst = new BST();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        back.setOnAction(e -> setBack(e));
        // LOAD BST

    }

//    private void loadBST()
//      // loads BST of the current profile for deletion

//    private void saveBST()
//      // Overwrite current profile BST


    private void setDelete(){
        //TODO: delete from database of current profile, If word does not exists, show error message.
        //      Save to database after deleting

    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"menu","Menu");
    }



}
