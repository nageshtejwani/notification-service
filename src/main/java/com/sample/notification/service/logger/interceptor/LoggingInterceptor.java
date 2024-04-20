package com.sample.notification.service.logger.interceptor;

import com.sample.notification.service.logger.RequestLog;
import com.sample.notification.service.logger.repository.RequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private final RequestLogRepository requestLogRepository;

    public LoggingInterceptor(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MultipleReadHttpServletRequest wrappedRequest = new MultipleReadHttpServletRequest(request);

        // Check if the request has been logged
        if (wrappedRequest.getAttribute("logged") == null) {
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = wrappedRequest.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            RequestLog requestLog = new RequestLog();
            requestLog.setBody(requestBody.toString());
            requestLogRepository.save(requestLog);

            // Mark the request as logged
            wrappedRequest.setAttribute("logged", true);
        }

        return true;
    }
}