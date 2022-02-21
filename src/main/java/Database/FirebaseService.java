package Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

public class FirebaseService {
    @PostConstruct
    public void initialize(){
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("lbycpa2-dictionary-database-firebase-adminsdk-jcyiu-5c618034b3.json");

            // I have no idea why it is deprecated, but it still works so ignore :>
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
