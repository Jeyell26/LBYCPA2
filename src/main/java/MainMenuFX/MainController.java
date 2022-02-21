package MainMenuFX;

import Tools.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Shows buttons to login or signup, and exit.

    @FXML
    Button login, signup, exit;
    // Buttons to navigate

    // for convenience
    private final Navigate x = new Navigate();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        login.setOnAction(e -> x.switchScene(e,"login","Login"));
        signup.setOnAction(e -> x.switchScene(e,"signup","Sign Up"));
        exit.setOnAction(this::setExit);
    }

    private void setExit(ActionEvent e){
        //TODO: Exit the code if clicked
        ((Stage) ((Node)e.getSource()).getScene().getWindow()).close();
    }

}
