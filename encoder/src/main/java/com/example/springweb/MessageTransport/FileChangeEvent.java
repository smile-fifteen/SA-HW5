package com.example.springweb.MessageTransport;

import org.springframework.context.ApplicationEvent;

public class FileChangeEvent extends ApplicationEvent {
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String filename;
    private String type;
    public FileChangeEvent(Object source, final String filename, final String type) {
        super(source);
        this.filename = filename;
        this.type =type;
    }
}
