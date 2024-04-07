package com.sample.notification.service.proxy;

import com.sample.notification.service.dto.ChannelTypes;
import com.sample.notification.service.dto.Notification;

public interface ChannelProxy {

    void send(Notification notiification);
}
