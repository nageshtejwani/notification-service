package com.sample.notification.service.monitoring;

import com.sample.notification.service.dto.ChannelTypes;
import com.sample.notification.service.dto.HealthStatus;

public interface HealthService {
    HealthStatus notifyHealth(ChannelTypes channelTypes);

}
