package com.example.springweb.MessageTransport;

public class Event {
    String filename;
    String type;

    public Event(String filename, String type) {
        this.filename = filename;
        this.type = type;
    }

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
}
