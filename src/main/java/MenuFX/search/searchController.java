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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class searchController implements Initializable {
    Stage stage;

    // Make sure you use the proper FXML IDs!
    Navigate x = new Navigate();
    // TextField to input word to search
    @FXML
    TextField word;
    // Should show all results from each database
    // Use a scroll wheel to be able to view all inputs
    // IF possible, add a Like button and sort it by likes (descending)

    // Show the current user's definition if they have the word on their database
    // If not show "This word has not been defined by the user yet"

    // If the word is not mentioned in the database INPUT word has not been added by anyone yet.

    // This is by far the hardest one to implement.

    @FXML
    Text wordChosen;

    @FXML
    Label error, userDefinition, otherDefinitions;

    @FXML
    Button search, back, like;
    // Buttons to complete process


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Initialize buttons here
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

        back.setOnAction(e -> setBack(e));
    }

    private void setSearch(ActionEvent event, String word) throws ExecutionException, InterruptedException {
        //TODO: Check database if word exists. If yes, display. If no, display empty
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        BSTree mainUserTree = getData((String) stage.getUserData());
        otherDefinitions.setText("");

        //      Set otherDefinitions to show in the scrollpane
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("User Database").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        ArrayList<BSTree> otherDef = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            if (!document.getId().equals((String) stage.getUserData())) otherDef.add(getData(document.getId()));
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
    private static BSTree MapToTree(BSTree tree, Map<String, Object> map){
        for(String key: map.keySet()){
            tree.insert(key, (String) map.get(key));
        }
        return tree;
    }

    //gets the data from the database also converts it to tree
    private static BSTree getData(String input) throws ExecutionException, InterruptedException {
        BSTree tree = new BSTree(input);
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("User Database").document(input);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            MapToTree(tree, document.getData());
        } else {
            System.out.println("No such document!");
        }
        return tree;
    }

    private void setBack(ActionEvent e){
        //TODO: Go back to MainMenuFX
        x.switchScene(e,"menu","Menu");
    }
}
