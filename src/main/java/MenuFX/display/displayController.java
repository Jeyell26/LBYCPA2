package MenuFX.display;

import BST.BSTree;
import Tools.Navigate;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class displayController implements Initializable {
    Navigate x = new Navigate();

    // TextField to input word to search
    // Should show all words from this database

    @FXML
    RadioButton userWords, databaseWords;

    @FXML
    Label otherDefinitions, error;

    @FXML
    Button moveToSearch, back, changeDatabase;
    // Buttons to complete process


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(this::setBack);
        moveToSearch.setOnAction(this::setSearch);
        changeDatabase.setOnAction(e -> {
            try {
                setDisplay(e);
            } catch (ExecutionException | InterruptedException executionException) {
                executionException.printStackTrace();
            }
        });
    }

    private void setDisplay(ActionEvent event) throws ExecutionException, InterruptedException {
        // Output var
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        // Current user variables
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BSTree mainUserTree = getData((String) stage.getUserData());
        // Other user variables
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("User Database").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        ArrayList<BSTree> otherDef = new ArrayList<>();

        // IF BOTH
        if(userWords.isSelected() && databaseWords.isSelected()){
            // If user database is empty
            if(mainUserTree.isEmpty()){
                error.setText("User has no words yet in database");
                error.setStyle("-fx-text-fill: red");
                error.setVisible(true);
                return;
            }
            // Save all in one array of trees
            for (QueryDocumentSnapshot document : documents) {
                otherDef.add(getData(document.getId()));
            }
            for(int i = 0; i < documents.size() - 1; i++) {
                temp = otherDef.get(i).inorder(otherDef.get(i).root,temp);
                for(String t: temp){
                    String add = t.substring(0,1).toUpperCase() + t.substring(1);
                    if(!output.contains(add)){
                        output.add(add);
                    }
                }
            }
            Collections.sort(output);
            error.setText("Currently showing all the words..");
            error.setStyle("-fx-text-fill: black");
            error.setVisible(true);
        }

        // IF ONLY USER
        if(userWords.isSelected() && !databaseWords.isSelected()){
            if(mainUserTree.isEmpty()){
                error.setText("User has no words yet in database");
                error.setStyle("-fx-text-fill: red");
                error.setVisible(true);
                return;
            }
            temp = mainUserTree.inorder(mainUserTree.root, temp);
            for(String t: temp){
                output.add(t.substring(0,1).toUpperCase() + t.substring(1));
            }
            error.setText("Currently showing " + stage.getUserData() + "'s words..");
            error.setStyle("-fx-text-fill: black");
            error.setVisible(true);
        }

        // IF ONLY DATABASE
        if(!userWords.isSelected() && databaseWords.isSelected()){
            for (QueryDocumentSnapshot document : documents) {
                if (!document.getId().equals(stage.getUserData())) otherDef.add(getData(document.getId()));
            }
            for(int i = 0; i < documents.size() - 1; i++) {
               temp = otherDef.get(i).inorder(otherDef.get(i).root,temp);
               for(String t: temp){
                   String add = t.substring(0,1).toUpperCase() + t.substring(1);
                   if(!output.contains(add)){
                       output.add(add);
                   }
               }
            }
            Collections.sort(output);
            error.setText("Currently showing the database' words..");
            error.setStyle("-fx-text-fill: black");
            error.setVisible(true);
        }

        StringBuilder stringTemp = new StringBuilder();
        for(String t: output){
            stringTemp.append(t).append("\n");
        }
        otherDefinitions.setText(stringTemp.toString());
    }

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

    private static void MapToTree(BSTree tree, Map<String, Object> map){
        for(String key: map.keySet()){
            tree.insert(key, (String) map.get(key));
        }
    }

    private void setSearch(ActionEvent e){
        x.switchScene(e,"search","Search for a definition");
    }

    private void setBack(ActionEvent e){
        x.switchScene(e,"menu","Menu");
    }
}
