package com.example.springweb.MessageTransport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class FileChangeSource implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher publisher;



    public void sendMessage(String filename, String type)
    {
        FileChangeSource.publisher.publishEvent(new FileChangeEvent(this,filename,type));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        FileChangeSource.publisher = applicationEventPublisher;
    }
}
