package MenuFX.search;

import BST.BSTree;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class searchController implements Initializable {
    Stage stage;

    Navigate x = new Navigate();
    // TextField to input word to search
    @FXML
    TextField word;

    @FXML
    Text wordChosen;

    @FXML
    Label error, userDefinition, otherDefinitions;

    @FXML
    Button search;
    @FXML
    Button back;
    // Buttons to complete process


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setOnAction(e ->{
            try {
                if(word.getText().isEmpty()){
                    error.setText("Please input word");
                    error.setStyle("-fx-text-fill: red");
                    return;
                }
                setSearch(e, word.getText());
                if(otherDefinitions.getText().isBlank()){
                    otherDefinitions.setText("Other users have not added this word yet.");
                }
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(this::setBack);
    }

    private void setSearch(ActionEvent event, String word) throws ExecutionException, InterruptedException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        BSTree mainUserTree = getData((String) stage.getUserData());
        otherDefinitions.setText("");

        //      Set otherDefinitions to show in the scroll pane
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("User Database").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        ArrayList<BSTree> otherDef = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            if (!document.getId().equals(stage.getUserData())) otherDef.add(getData(document.getId()));
        }

        // for main user
        if(mainUserTree.search(word) != null){
            BSTree.Node node = mainUserTree.search(word);
            userDefinition.setText("WORD: " + node.title + "\n" + "DEFINITION: " + node.definition);
        }else userDefinition.setText("Word does not exist for Current User");

        // for others
        for(int i = 0; i < documents.size() - 1; i++) {
            if (otherDef.get(i).search(word) != null) {
                BSTree.Node node = otherDef.get(i).search(word);
                String prev = otherDefinitions.getText();
                otherDefinitions.setText(prev + "DEFINITION: " + node.definition
                + "\nBY: " + otherDef.get(i).owner + "\n\n");
            }
        }

        error.setText("Other Definitions");
        error.setStyle("-fx-text-fill: black");

    }

    //converts database data fields to tree
    private static void MapToTree(BSTree tree, Map<String, Object> map){
        for(String key: map.keySet()){
            tree.insert(key, (String) map.get(key));
        }
    }

    //gets the data from the database also converts it to tree
    private static BSTree getData(String input) throws ExecutionException, InterruptedException {
        BSTree tree = new BSTree(input);
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Database").document(input);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            MapToTree(tree, Objects.requireNonNull(document.getData()));
        } else {
            System.out.println("No such document!");
        }
        return tree;
    }

    private void setBack(ActionEvent e){
        x.switchScene(e,"menu","Menu");
    }
}
