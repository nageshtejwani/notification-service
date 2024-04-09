package com.sample.notification.service.proxy;

import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Message;
import com.sample.notification.service.dto.Notification;

public interface ChannelProxy {

    void send(ChannelType channelType, Message message);
}
