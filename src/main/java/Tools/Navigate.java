package Tools;

import MenuFX.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigate {
    // This class is purely for convenience purposes

    // Switch scene without changing the title
    public void switchScene(ActionEvent event, String filename){
        Stage stage, currStage;
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/"+filename+".fxml")));
            currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            System.out.println(currStage.getUserData());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setUserData(currStage.getUserData());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switch scene with title
    public void switchScene(ActionEvent event, String filename, String title){
        Stage stage, currStage;
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/"+filename+".fxml")));
            currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            System.out.println(currStage.getUserData());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setUserData(currStage.getUserData());
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
