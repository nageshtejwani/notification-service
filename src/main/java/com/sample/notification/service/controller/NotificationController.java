package com.sample.notification.service.controller;


import com.sample.notification.service.channels.factory.ChannelBeanFactory;
import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Notification;
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


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);


    private final ChannelBeanFactory channelBeanFactory;

    public NotificationController(ChannelBeanFactory channelBeanFactory) {
        this.channelBeanFactory = channelBeanFactory;
    }

    @RequestMapping("/send")
    public ResponseEntity<String> sendNotification( @RequestBody Notification notification) {
        LOGGER.info("Sending notification: {}", notification);
        for (ChannelType channelType : notification.getChannelTypes()) {
            channelBeanFactory.getChannelTypeBean(channelType).send(notification.getMessage());

        }
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

    @GetMapping("/notifytest")
    public ResponseEntity<String> testService() {
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

}
