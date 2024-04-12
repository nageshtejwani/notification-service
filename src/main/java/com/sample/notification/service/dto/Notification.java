package com.sample.notification.service.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification {
    private Message message;
    private List<ChannelType> channelTypes;
}
