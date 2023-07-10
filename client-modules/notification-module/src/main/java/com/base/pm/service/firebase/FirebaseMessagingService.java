package com.base.pm.service.firebase;

import com.base.pm.pojo.FirebaseNotificationMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class FirebaseMessagingService implements FirebaseNotificationSender {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void sendNotification(FirebaseNotificationMessage notificationMessage) {

        Notification notification = Notification
                .builder().
                setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody()).
                setImage(notificationMessage.getImageUrl())
                .build();

        Message message = Message
                .builder()
                .setNotification(notification)
                .setToken(notificationMessage.getToken())
                .putAllData(notificationMessage.getData())
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
