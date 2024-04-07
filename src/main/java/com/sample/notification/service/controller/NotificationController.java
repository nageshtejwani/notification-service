package com.sample.notification.service.controller;


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
        switch (healthService.notifyHealth(notification.getChannelTypes())) {
            case UP:
                channel.send(notification);
                break;
            case DOWN:
                return new ResponseEntity<>("Notification failed", HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                return new ResponseEntity<>("Notification failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

}
