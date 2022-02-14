package MenuFX.add;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class addController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // TextField to input word and definition
    // Should add to existing Database

    @FXML
    TextField word, definition;

    @FXML
    Text error;

    @FXML
    Button add, back;
    // Buttons to complete process

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        System.out.println("hey");
    }

    private void setAdd(){
        //TODO: Adds to database of current profile, If word already exists, show error message. Tip to go to edit instead


    }

    private void setBack(){
        //TODO: Go back to main menu fx

    }


}
