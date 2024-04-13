package com.sample.notification.service.proxy;

import com.sample.notification.service.channels.Channel;
import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ChannelProxyImpl implements ChannelProxy {

    private final Channel email;
    private final Channel discord;
    private final Channel slack;
    private final Channel msTeams;

    @Autowired
    public ChannelProxyImpl(@Qualifier("email") Channel email,
                            @Qualifier("discord") Channel discord, @Qualifier("slack") Channel slack, @Qualifier("msteams") Channel msTeams) {
        this.email = email;
        this.discord = discord;
        this.slack = slack;
        this.msTeams = msTeams;
    }

    @Override
    public void send(ChannelType channelType, Message message) {
        switch (channelType) {
            case EMAIL:
                email.send(message);
                break;
            case DISCORD:
                discord.send(message);
                break;
            case SLACK:
                slack.send(message);
                break;
            case MSTeams:
                msTeams.send(message);
                break;
        }
    }
}
