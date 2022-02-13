package Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseService {
    @PostConstruct
    public void initialize() throws IOException {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("test-ff249-firebase-adminsdk-u7033-44daf73f8c.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
