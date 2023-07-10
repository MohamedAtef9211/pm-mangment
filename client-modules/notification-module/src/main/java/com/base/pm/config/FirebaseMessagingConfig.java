package com.base.pm.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseMessagingConfig {

    @Bean
    public FirebaseMessaging getFireBaseMessaging() throws IOException {
        FirebaseOptions options = new FirebaseOptions
                .Builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("firebase-messaging-firebase-adminsdk-8tpvm-e7e3514c2d.json")
                        .getInputStream()))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options, "firebase-app");
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
