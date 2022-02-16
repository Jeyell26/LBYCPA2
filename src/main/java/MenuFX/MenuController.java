package MenuFX;

import Tools.Navigate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    // Main menu after logging in
    // Includes Adding a word, Deleting a word, Editing a word, Searching a word, and Exit.
    // Remember that each button click is a new window, therefore a new controller.
    Navigate x = new Navigate();

    @FXML
    Button add, delete, edit, search, back, exit;

    // can be showed to show the name of current user
    // currently used as indicator of who is the current user logged in.
    Label user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons
        add.setOnAction(e -> x.switchScene(e,"add"));
        delete.setOnAction(e -> x.switchScene(e,"delete"));
        edit.setOnAction(e -> x.switchScene(e,"edit"));
        search.setOnAction(e -> x.switchScene(e, "MenuFX/search"));
        back.setOnAction(e -> x.switchScene(e,"main"));
        exit.setOnAction(e -> Platform.exit());
    }

    // just to pass the name of current user to other controllers
    public void currentUser(String input){
        user.setText(input);
    }



}
