package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Notification;

public class Email implements Channel{
    @Override
    public void send(Notification notification) {

    }

    @Override
    public String getStatus() {
        return null;
    }
}
