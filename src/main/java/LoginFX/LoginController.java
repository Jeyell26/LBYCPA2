package LoginFX;

import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
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
    Button login, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here

        login.setOnAction(e -> {
            try {
                if (loginUser(user.getText(),pass.getText())){
                    setLogin(user.getText());
                }
            } catch (ExecutionException | InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e -> setBack());

        //TODO: Load from database here (to check if login is valid)

    }

    private void setBack(){
        //TODO: Go back to MainMenuFX

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }

    private void setLogin(String input) throws IOException {
        //TODO: If user and pass is valid, proceed to MenuFX. If not, Show error message.

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")));
        stage.setUserData(input);
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
        stage.show();

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
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Details").document(user);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            if(pass.equals(document.getString("password"))) {
                return true;
            }
            else {
                System.out.println("Wrong Password");
                return false;
            }
        } else {
            System.out.println("User does not exist");
            return false;
        }
    }

}
