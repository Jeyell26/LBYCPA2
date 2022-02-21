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
import javafx.scene.control.*;

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
    TextField user, showPass;

    @FXML
    PasswordField pass;

    @FXML
    Label error;

    @FXML
    CheckBox showToggle;

    @FXML
    Button signUp, back;
    // Buttons to navigate

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showPass.setVisible(false);
        pass.setVisible(true);
        showToggle.setOnAction(this::togglePass);


        //TODO: Initialize buttons here
        signUp.setOnAction(e -> {
            try {
                if(getPass().isBlank() || user.getText().isBlank()){
                    error.setText("Username or Password fields cannot be empty");
                    error.setStyle("-fx-text-fill: red");
                }
                else if (verifyUser(user.getText())) {
                    setSignUp(e, user.getText(), getPass());
                }
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(this::setBack);
    }

    public void togglePass(ActionEvent event){
        if(showToggle.isSelected()){
            // show the text
            showPass.setText(pass.getText());
            showPass.setVisible(true);
            pass.setVisible(false);
            return;
        }
        pass.setText(showPass.getText());
        showPass.setVisible(false);
        pass.setVisible(true);
    }

    private String getPass(){
        if(showToggle.isSelected()){
            return showPass.getText();
        }
        return pass.getText();
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
            error.setText("User already exists in the database");
            error.setStyle("-fx-text-fill: red");
            return false;
        }
        else return true;
    }

}
