package com.example.springweb.MessageTransport;

import com.example.springweb.HandBrake.FileDelete;
import com.example.springweb.HandBrake.handbrake;
import com.example.springweb.dao.VideoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FileChangeListener {
    @Autowired
    VideoService videoService;

    @RabbitListener(queues = "fileQueue")
    public void fileChange(String in) throws InterruptedException {
        if(in.compareTo("start") == 0) return;
        String[] event = in.split(" ");
        System.out.println("In Listener: " + event[0]);
        System.out.println("In Listener: " + event[1]);
        if(event[1].compareTo("ENTRY_CREATE") == 0) {
            handbrake hb = new handbrake(event[0],videoService);
            hb.start();
            //hb.join();
        }
        else
        {
            FileDelete fd = new FileDelete();
            fd.Delete(event[0]);
        }
    }

//    @RabbitListener(queues = "fileQueue")
//    public void listen(String in)
//    {
//        System.out.println(in);
//    }
}
