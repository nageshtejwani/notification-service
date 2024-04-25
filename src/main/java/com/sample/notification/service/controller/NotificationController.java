package com.sample.notification.service.controller;

import com.sample.notification.service.channels.factory.ChannelBeanFactory;
import com.sample.notification.service.channels.service.RequestLogService;
import com.sample.notification.service.dto.Notification;
import com.sample.notification.service.logger.RequestLog;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    private final ChannelBeanFactory channelBeanFactory;
    private final RequestLogService requestLogService;

    public NotificationController(ChannelBeanFactory channelBeanFactory, RequestLogService requestLogService) {
        this.channelBeanFactory = channelBeanFactory;
        this.requestLogService = requestLogService;
    }

    @RequestMapping("/send")
    @Operation(summary = "Sending Notification", description = "Send notification to multiple channels")
    public Mono<ResponseEntity<String>> sendNotification(@RequestBody Notification notification) {
        LOGGER.info("Sending notification: {}", notification);
        return Flux.fromIterable(notification.getChannelTypes())
                .flatMap(channelType -> Mono.fromRunnable(() -> channelBeanFactory.getChannelTypeBean(channelType).send(notification.getMessage())))
                .then(Mono.just(new ResponseEntity<>("Notification sent successfully", HttpStatus.OK)));
    }

    @GetMapping("/notifytest")
    public ResponseEntity<String> testService() {
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

    @GetMapping("/requestLogs")
    public List<RequestLog> getRequestLogs() {
        return requestLogService.findAll();
    }

}