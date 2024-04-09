package com.sample.notification.service.monitoring;

import com.sample.notification.service.dto.ChannelType;
import com.sample.notification.service.dto.HealthStatus;

public interface HealthService {
    HealthStatus notifyHealth(ChannelType channelType);

}
