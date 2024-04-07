package com.sample.notification.service.proxy;

import com.sample.notification.service.dto.ChannelTypes;
import com.sample.notification.service.dto.Notification;
import org.springframework.stereotype.Component;

@Component
public class ChannelProxyImpl implements ChannelProxy{
    @Override
    public void send(Notification notification) {
        switch (notification.getChannelTypes()){
            case EMAIL:
                System.out.println("Email sent: " + notification.getMessage());
                break;
            case SMS:
                System.out.println("SMS sent: " + notification.getMessage());
                break;
            case SLACK:
                System.out.println("Slack sent: " + notification.getMessage());
                break;
        }
    }
}
