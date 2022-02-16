package LoginFX;

import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginController implements Initializable {

    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();

    // Username and Password textFields
    @FXML
    TextField user;

    @FXML
    PasswordField pass;

    @FXML
    Label error;

    @FXML
    Button login, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setWrapText(true);
        //TODO: Initialize buttons here
        login.setOnAction(e -> {
            try {
                if (loginUser(user.getText(),pass.getText())){
                    setLogin(e,user.getText());
                }
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e -> setBack(e));

        //TODO: Load from database here (to check if login is valid)

    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"main","Main Menu",user.getText());
    }

    private void setLogin(ActionEvent e, String input) {
        //TODO: If user and pass is valid, proceed to MenuFX. If not, Show error message.
        x.switchScene(e,"menu","Menu",input);
    }

    // verifies if user exists in the database
    private Boolean verifyUser(String user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Details").document(user);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return !document.exists();
    }

    // whether inputted user details matches
    private Boolean loginUser(String user, String pass) throws ExecutionException, InterruptedException {
        if(user.equals("")||pass.equals("")){
            error.setText("Please input username/password");
            error.setStyle("-fx-text-fill: red");
            return false;
        }
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Details").document(user);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        this.user.clear();
        this.pass.clear();
        if (document.exists()) {
            if(pass.equals(document.getString("password"))) {
                error.setText("Logging in..");
                error.setStyle("-fx-text-fill: green");
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
            error.setText("Username does not exit in our database");
            error.setStyle("-fx-text-fill: red");
            return false;
        }
    }

}
