package MainMenuFX.LoginFX;

import MenuFX.MenuController;
import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginController implements Initializable {

    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();

    Stage stage;

    // Username and Password textFields
    @FXML
    TextField user;

    @FXML
    PasswordField pass;

    @FXML
    Label error;

    @FXML
    Button login, back;

    @FXML
    ProgressBar loading;
    double progress;
    // Buttons to navigate

    // Database
    Firestore db = FirestoreClient.getFirestore();
    CollectionReference cr = db.collection("User Details");
    DocumentReference docRef = cr.document("_");
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document;

    {
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loading.setVisible(false);
        progress = 0;
        error.setWrapText(true);
        //TODO: Initialize buttons here
        login.setOnAction(e -> {
            loading.setVisible(true);
            boolean temp = false;
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            try {
                temp = loginUser(user.getText(),pass.getText());
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
            if (temp){
                loadProgress(.20);
                Platform.runLater(() -> setLogin(e,user.getText()));
            }
            else{
                loadProgress(0);
                loading.setVisible(false);
            }
            this.user.clear();
            this.pass.clear();
        });

        back.setOnAction(e -> setBack(e));

        //TODO: Load from database here (to check if login is valid)

    }



    private void loadProgress(double inp){
        progress += inp;
        if (!(progress < 1)) {
            progress = 1;
        }
        if(inp == 0){
            progress = 0;
        }
        loading.setProgress(progress);


    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"main","Main Menu");
    }

    private void setLogin(ActionEvent e, String input) {
        //TODO: If user and pass is valid, proceed to MenuFX. If not, Show error message.
        x.switchScene(e,"menu","Menu",input);
    }

    // whether inputted user details matches
    private Boolean loginUser(String user, String pass) throws ExecutionException, InterruptedException {
        if(user.equals("")||pass.equals("")){
            error.setText("Please input username/password");
            error.setStyle("-fx-text-fill: red");
            return false;
        }


        docRef = cr.document(user);
        future = docRef.get();
        document = future.get();

        if (document.exists()) {
            if(pass.equals(document.getString("password"))) {
                return true;
            }
            else {
                System.out.println("Wrong Password");
                error.setText("Wrong Password");
                error.setStyle("-fx-text-fill: red");
                return false;
            }
        } else {
            System.out.println("User does not exist");
            error.setText("Username does not exist in our database");
            error.setStyle("-fx-text-fill: red");
            return false;
        }

    }

}
