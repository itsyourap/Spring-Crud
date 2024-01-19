package com.crud.serverside.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Bean
    public FirebaseApp initializeFirebaseApp() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();
            return FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            return null;
        }
    }
}
