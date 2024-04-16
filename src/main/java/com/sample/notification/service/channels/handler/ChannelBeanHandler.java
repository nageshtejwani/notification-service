package com.sample.notification.service.channels.handler;

import com.sample.notification.service.dto.ChannelType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ChannelBeanHandler{
    ChannelType type();
}