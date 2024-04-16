package com.sample.notification.service.channels.factory;

import com.sample.notification.service.channels.Channel;
import com.sample.notification.service.channels.handler.ChannelBeanHandler;
import com.sample.notification.service.dto.ChannelType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChannelBeanFactory implements ApplicationContextAware, InitializingBean {


    private final ListableBeanFactory listableBeanFactory;

    private ApplicationContext applicationContext;

    protected Map<ChannelType, Channel> typeBeanMap = new HashMap<>();

    public ChannelBeanFactory(ListableBeanFactory listableBeanFactory) {
        this.listableBeanFactory = listableBeanFactory;
    }


    public Channel getChannelTypeBean(ChannelType channelType) {
        return typeBeanMap.get(channelType);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initializeTypeBeanMap();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



    private void initializeTypeBeanMap() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ChannelBeanHandler.class);

        for (String beanName : beansWithAnnotation.keySet()) {

            Channel channel = listableBeanFactory.getBean(beanName, Channel.class);
            ChannelBeanHandler beanHandler = listableBeanFactory.findAnnotationOnBean(beanName, ChannelBeanHandler.class);
            ChannelType channelType = beanHandler.type();
            typeBeanMap.put(channelType, channel);
        }
    }
}