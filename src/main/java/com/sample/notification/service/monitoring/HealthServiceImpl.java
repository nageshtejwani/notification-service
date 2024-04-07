package com.sample.notification.service.monitoring;

import com.sample.notification.service.dto.ChannelTypes;
import com.sample.notification.service.dto.HealthStatus;
import org.springframework.stereotype.Component;

@Component
public class HealthServiceImpl implements HealthService {
    @Override
    public HealthStatus notifyHealth(ChannelTypes channelTypes) {
        return HealthStatus.UP;
    }
}
