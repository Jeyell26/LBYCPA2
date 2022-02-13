package LoginFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // Username and Password textFields
    @FXML
    TextField user;

    @FXML
    PasswordField pass;

    @FXML
    Button login, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here

        //TODO: Load from database here (to check if login is valid)

    }

    private void setBack(){
        //TODO: Go back to MainMenuFX

    }

    private void setLogin(){
        //TODO: If user and pass is valid, proceed to MenuFX. If not, Show error message.
    }

}
