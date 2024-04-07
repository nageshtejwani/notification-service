package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Notification;

public interface Channel {
    void send(Notification notification);
    String getStatus();

}
