package com.base.pm.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirebaseNotificationMessage {
    private String token;
    private String title;
    private String body;
    private String imageUrl;
    private Map<String,String> data;
}
