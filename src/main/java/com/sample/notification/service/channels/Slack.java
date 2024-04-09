package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Message;
import com.sample.notification.service.dto.Notification;
import org.springframework.stereotype.Component;

@Component("slack")
public class Slack implements Channel{
    @Override
    public void send(Message message) {
        System.out.println("Slack sent: " + message.getMessage());

    }


}
