package MenuFX.edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class editController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // TextField to input word and definition
    // Should add to existing Database

    @FXML
    TextField word, definition;

    @FXML
    Text errorMessage;

    @FXML
    Button edit, back;
    // Buttons to complete process

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here

    }

    private void setEdit(){
        //TODO: Check database if word exists. If yes, edit. If no, show error message

    }

    private void setBack(){
        //TODO: Go back to main menu fx

    }

}
