package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Message;
import com.sample.notification.service.dto.Notification;
import org.springframework.stereotype.Component;


@Component("msteams")
public class MSTeams implements Channel{
    @Override
    public void send(Message message) {
        System.out.println("MSTeams sent: " + message.getMessage());

    }

}
