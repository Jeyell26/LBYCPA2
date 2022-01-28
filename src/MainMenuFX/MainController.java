package MainMenuFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // Shows buttons to login or signup, and exit.

    @FXML
    Button login, signup, exit;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
    }

    private void setSignup(){
        //TODO: Transfer to SignUp menu if clicked
    }

    private void setLogin(){
        //TODO: Transfer to Login menu if clicked
    }

    private void setExit(){
        //TODO: Exit the code if clicked
    }

}
