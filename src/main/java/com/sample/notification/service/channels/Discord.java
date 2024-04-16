package com.sample.notification.service.channels;

import com.sample.notification.service.channels.handler.ChannelBeanHandler;
import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Component("discord")
@ChannelBeanHandler(type = ChannelType.DISCORD)

public class Discord implements Channel{
    /**
     * @param message
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Discord.class);

    private final WebClient webClient;



    public Discord(@Value("${channels.discord.webhook-url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    @Override
    public void send(Message message) {
        String json = "{" +
                "\"content\":\"" +
                message.getBody() +
                "\"" +
                "}";

        webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(json))
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> LOGGER.info("Message sent to Discord: " + message.getBody()))
                .doOnError(e -> LOGGER.error("Failed to send message to Discord: ", e))
                .subscribe();
    }
}