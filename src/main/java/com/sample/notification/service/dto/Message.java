package com.sample.notification.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String message;
    private String subject;
    private String toEmail;
    private String slackChannel;
    private String cellNummber;
    private String msteamsChannel;

}
