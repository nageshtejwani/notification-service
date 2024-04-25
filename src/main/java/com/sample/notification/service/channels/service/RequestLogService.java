package com.sample.notification.service.channels.service;
import com.sample.notification.service.logger.RequestLog;
import com.sample.notification.service.logger.repository.RequestLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestLogService {
    private final RequestLogRepository requestLogRepository;

    public RequestLogService(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    public List<RequestLog> findAll() {
        return requestLogRepository.findAll();
    }
}
