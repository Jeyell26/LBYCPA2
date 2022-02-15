package MainMenuFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    // Main runnable file
    // Should launch LoginController

    Database.FirebaseService firebase = new Database.FirebaseService();


    @Override
    public void start(Stage primaryStage) throws Exception{

        // initialization of the Firebase Database
        firebase.initialize();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main.fxml")));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
