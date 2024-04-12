package com.sample.notification.service.dto;


import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;



@NoArgsConstructor
@ToString
@Getter
@Setter
public class Message {
    private String body; ;
    private String subject;
    private String toEmail;
    private String slackChannel;
    private String cellNummber;
    private String msteamsChannel;

}
