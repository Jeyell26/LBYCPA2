package MainMenuFX.SignUpFX;

import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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
        signUp.setOnAction(e -> {
            try {
                if (verifyUser(user.getText())) {
                    setSignUp(e, user.getText(), pass.getText());
                }
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e -> setBack(e));
    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"main","Main Menu");
    }

    private void setSignUp(ActionEvent e, String user, String pass){
        Firestore db = FirestoreClient.getFirestore();
        Map<String, String> dictionary = new HashMap<>();
        Map<String, String> details = new HashMap<>();
        details.put("password", pass);
        db.collection("User Database").document(user).set(dictionary);
        db.collection("User Details").document(user).set(details);

        // Goes back after signing up. Maybe after a prompt saying "Successfully signed up"
        setBack(e);
    }

    // Whether user already exists within the database
    private Boolean verifyUser(String user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Details").document(user);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            //TODO: Put error message saying "User already exists in the database

            return false;
        }
        else return true;
    }

}
