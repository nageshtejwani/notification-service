package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Notification;
import org.springframework.stereotype.Component;

@Component
public class Slack implements Channel{
    @Override
    public void send(Notification notification) {

    }


}
