package MainMenuFX;

import Tools.Navigate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // Make sure you use the proper FXML IDs!

    // Shows buttons to login or signup, and exit.

    @FXML
    Button login, signup, exit;
    // Buttons to navigate

    // for convenience
    private Navigate x = new Navigate();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        login.setOnAction(e -> x.switchScene(e,"login","Login"));
    }



    private void setSignup(){
        //TODO: Transfer to SignUp menu if clicked
    }

    private void setLogin() throws IOException {
        //TODO: Transfer to Login menu if clicked

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setExit(){
        //TODO: Exit the code if clicked
    }

}
