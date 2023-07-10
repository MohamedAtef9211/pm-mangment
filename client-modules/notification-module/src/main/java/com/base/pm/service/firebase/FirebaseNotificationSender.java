package com.base.pm.service.firebase;


import com.base.pm.pojo.FirebaseNotificationMessage;

public interface FirebaseNotificationSender {
    void sendNotification(FirebaseNotificationMessage notificationMessage);
}
