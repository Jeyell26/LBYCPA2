package SignUpFX;

import Tools.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    // Make sure you use the proper FXML IDs!

    Navigate x = new Navigate();

    // Username and Password textFields
    @FXML
    TextField user, pass;

    @FXML
    Button signUp, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        signUp.setOnAction(e -> setSignUp(e,user.getText(),pass.getText()));

        back.setOnAction(e -> setBack(e));
    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"main","Main Menu",user.getText());
    }

    private void setSignUp(ActionEvent e, String user, String pass){
        //TODO: Adds new user and pass in the database and goes back to mainmenuFX
        // Goes back after signing up. Maybe after a prompt saying "Successfully signed up"
        setBack(e);
    }

}
