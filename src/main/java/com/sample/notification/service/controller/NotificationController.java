package com.sample.notification.service.controller;


import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Notification;
import com.sample.notification.service.monitoring.HealthService;
import com.sample.notification.service.proxy.ChannelProxy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/notification")
public class NotificationController {


    private final HealthService healthService;
    private final ChannelProxy channel;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    public NotificationController(HealthService healthService, ChannelProxy channel) {
        this.healthService = healthService;
        this.channel = channel;
    }

    @RequestMapping("/send")
    public ResponseEntity<String> sendNotification( @RequestBody Notification notification) {
        LOGGER.info("Sending notification: {}", notification);
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

    @GetMapping("/notifytest")
    public ResponseEntity<String> testService() {
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

}
