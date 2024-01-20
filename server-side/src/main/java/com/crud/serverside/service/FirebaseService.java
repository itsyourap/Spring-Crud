package com.crud.serverside.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;

@Service
public class FirebaseService {

    @Bean
    public FirebaseApp initializeFirebaseApp() {
        try {
            String APPLICATION_CREDENTIAL = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new ByteArrayInputStream(Base64.getDecoder().decode(APPLICATION_CREDENTIAL)))
                    )
                    .build();
            return FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            return null;
        }
    }
}
