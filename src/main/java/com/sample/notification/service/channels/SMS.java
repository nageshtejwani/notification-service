package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Message;
import com.sample.notification.service.dto.Notification;
import org.springframework.stereotype.Component;

@Component("sms")
public class SMS implements Channel{
    @Override
    public void send(Message message) {
        System.out.println("SMS sent: " + message.getMessage());

    }

}
