package com.sample.notification.service.controller;


import com.sample.notification.service.dto.Notification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;


@RestController
public class NotificationController {



     public ResponseEntity<String> sendNotification(Notification notification) {


         return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
     }

}
