package MenuFX.delete;

import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class deleteController implements Initializable {
    Stage stage;

    Navigate x = new Navigate();
    // TextField to input word and definition
    // Should add to existing Database

    @FXML
    TextField word;

    @FXML
    Label error;

    @FXML
    Button delete, back;
    // Buttons to complete process

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setText("");
        error.setWrapText(true);
        delete.setOnAction(e -> {
            try {
                if(word.getText().isEmpty()){
                    error.setText("Please input word");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                if (verifyWord(e, word.getText())){
                    error.setText("Word does not exists");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                error.setText("Word deleted");
                error.setStyle("-fx-text-fill: green");
                setDelete(e, word.getText());
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        back.setOnAction(this::setBack);
    }


    private void setDelete(ActionEvent event, String word){
        String temp;
        temp = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
        word = temp;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference dr = db.collection("User Database").document((String) stage.getUserData());
        Map<String, Object> dictionary = new HashMap<>();
        dictionary.put(word, FieldValue.delete());
        dr.update(dictionary);
    }

    private Boolean verifyWord(ActionEvent event, String word) throws ExecutionException, InterruptedException{
        String temp;
        temp = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
        word = temp;
        Firestore db = FirestoreClient.getFirestore();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        DocumentReference docRef = db.collection("User Database").document((String) stage.getUserData());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()){
            for(String key: Objects.requireNonNull(document.getData()).keySet()){
                if (key.equals(word)){
                    return false;
                }
            }
        }
        return true;
    }

    private void setBack(ActionEvent e){
        x.switchScene(e,"menu","Menu");
    }



}
