package MenuFX;

import Tools.Navigate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    // Main menu after logging in
    // Includes Adding a word, Deleting a word, Editing a word, Searching a word, and Exit.
    // Remember that each button click is a new window, therefore a new controller.

    Navigate x = new Navigate();

    @FXML
    Button add, delete, edit, search, back, display;

    Stage stage;
    // can be showed to show the name of current user
    // currently used as indicator of who is the current user logged in.
    @FXML
    Text welcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(this::setWelcome);

        //TODO: Initialize buttons
        add.setOnAction(e -> x.switchScene(e,"add","Add a word"));
        delete.setOnAction(e -> x.switchScene(e,"delete","Delete an existing word"));
        edit.setOnAction(e -> x.switchScene(e,"edit","Edit an existing word"));
        search.setOnAction(e -> x.switchScene(e, "search","Search for a definition"));
        back.setOnAction(e -> x.switchScene(e,"main","Main menu"));
        display.setOnAction(e -> x.switchScene(e, "display", "Display Words"));
    }

    public void setWelcome(){
        stage = (Stage) welcome.getScene().getWindow();
        welcome.setText("Welcome to Binary Search Dictionary, " + stage.getUserData()+ "!\n" +
                "Let's get started!");
    }
}
