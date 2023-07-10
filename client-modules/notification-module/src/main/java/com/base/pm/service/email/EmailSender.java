package com.base.pm.service.email;


import com.base.pm.pojo.Email;

public interface EmailSender {

    void sendMail(Email email);
    void sendHtmlMail(Email email);
}
