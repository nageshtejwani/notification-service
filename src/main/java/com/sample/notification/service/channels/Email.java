package com.sample.notification.service.channels;

import com.sample.notification.service.channels.handler.ChannelBeanHandler;
import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Message;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component("email")
@ChannelBeanHandler(type = ChannelType.EMAIL)
public class Email implements Channel {

    private final JavaMailSender email;

    public Email(JavaMailSender email ) {
        this.email = email;
    }
    @Override
    public void send(Message message) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("abc@gmail.com");
        simpleMessage.setTo(message.getToEmail());
        simpleMessage.setSubject(message.getSubject());
        simpleMessage.setText(message.getBody());
        email.send(simpleMessage);
    }
}



