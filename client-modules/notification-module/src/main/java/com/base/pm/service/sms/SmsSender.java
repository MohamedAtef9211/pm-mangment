package com.base.pm.service.sms;


import com.base.pm.pojo.SmsMessage;

public interface SmsSender {

    void sendSms(SmsMessage message);
}
