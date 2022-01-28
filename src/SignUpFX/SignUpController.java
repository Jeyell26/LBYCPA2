package SignUpFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // Username and Password textFields
    @FXML
    TextField user, pass;

    @FXML
    Button signUp, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here

    }

    private void setBack(){
        //TODO: Go back to MainMenuFX


    }

    private void setSignUp(){
        //TODO: Adds new user and pass in the database and goes back to mainmenuFX

    }

}
