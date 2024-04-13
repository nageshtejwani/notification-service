package com.sample.notification.service.channels;

import com.sample.notification.service.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;


@Component("discord")
public class Discord implements Channel{
    /**
     * @param message
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Discord.class);

    private final WebClient webClient;

    public Discord(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://discord.com/api/webhooks/your/webhook/id/your/webhook/token").build();// Needs to fix with the yaml value
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
