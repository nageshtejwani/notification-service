package com.sample.notification.service.channels;

import com.sample.notification.service.channels.handler.ChannelBeanHandler;
import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Message;
import org.springframework.stereotype.Component;


@Component("msteams")
@ChannelBeanHandler(type = ChannelType.MSTEAMS)
public class MSTeams implements Channel{
    @Override
    public void send(Message message) {
        System.out.println("MSTEAMS sent: " + message.getBody());

    }

}
