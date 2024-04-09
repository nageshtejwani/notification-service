package com.sample.notification.service.controller;


import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Notification;
import com.sample.notification.service.monitoring.HealthService;
import com.sample.notification.service.proxy.ChannelProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {


    private final HealthService healthService;
    private final ChannelProxy channel;

    public NotificationController(HealthService healthService, ChannelProxy channel) {
        this.healthService = healthService;
        this.channel = channel;
    }

    public ResponseEntity<String> sendNotification(Notification notification) {
        for (ChannelType channelType : notification.getChannelTypes()) {
            switch (healthService.notifyHealth(channelType)) {
                case UP:
                    channel.send(channelType, notification.getMessage());
                    break;
                case DOWN:
                    continue;
                case UNKNOWN:
                    break;
                default:
                    continue;
            }
        }


        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

}
