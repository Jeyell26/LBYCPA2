package MenuFX.add;

import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class addController implements Initializable {

    Stage stage;

    Navigate x = new Navigate();

    // TextField to input word and definition
    // Should add to existing Database

    @FXML
    TextField word;

    @FXML
    TextArea definition;

    @FXML
    Label error;

    @FXML
    Button add, back;
    // Buttons to complete process

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
        error.setText("");
        error.setWrapText(true);
        add.setOnAction(e -> {
            try {
                if(word.getText().isEmpty()){
                    error.setText("Please input word");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                else if(definition.getText().isEmpty()){
                    error.setText("Please input definition");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                if (!verifyWord(e, word.getText())){
                    //TODO: GUI that shows "word already exists" and tip to go to edit instead
                    error.setText("Word already exists, go to edit instead");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                error.setText("Word added");
                error.setStyle("-fx-text-fill: green");
                System.out.println(definition.getText());
                setAdd(e,word.getText(),definition.getText());
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(this::setBack);

    }

    private void setAdd(ActionEvent event, String word, String def) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put(word,def);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        db.collection("User Database").document((String) stage.getUserData()).set(dictionary, SetOptions.merge());
    }

    private Boolean verifyWord(ActionEvent event, String word) throws ExecutionException, InterruptedException{
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
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"menu","Menu");
    }


}
