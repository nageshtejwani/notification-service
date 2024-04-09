package com.sample.notification.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    private Message message;
    List<ChannelType> channelTypes;
}
