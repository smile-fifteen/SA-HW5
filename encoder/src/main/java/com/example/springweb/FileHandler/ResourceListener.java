package com.example.springweb.FileHandler;

import com.example.springweb.MessageTransport.Event;
import com.example.springweb.MessageTransport.FileChangeSource;
import com.example.springweb.amqp.amqptest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ResourceListener implements CommandLineRunner {
    private static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
    private WatchService ws;
    private String listenerPath;
    private static String path = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\video\\raw";


    private ResourceListener() {
        try {
            ws = FileSystems.getDefault().newWatchService();
            this.listenerPath = path;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        fixedThreadPool.execute(new Listner(ws,this.listenerPath));
    }

    public static void addListener() throws IOException {
        ResourceListener resourceListener = new ResourceListener();
        Path p = Paths.get(path);
        p.register(resourceListener.ws,StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(path);
        ResourceListener.addListener();
    }
}

class Listner implements Runnable {
    private WatchService service;
    private String rootPath;



    public Listner(WatchService service,String rootPath) {
        this.service = service;
        this.rootPath = rootPath;
    }
    @Override
    public void run() {
        int count = 0;
        try {
            while(true){
                WatchKey watchKey = service.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for(WatchEvent<?> event : watchEvents){

                    //TODO 根据事件类型采取不同的操作。。。。。。。
                    System.out.println("["+rootPath+event.context()+"]文件发生了["+event.kind()+"]事件"+    count);
                    String filename = event.context()+"";
                    String type = event.kind()+"";
                    FileChangeSource fileChangeSource = new FileChangeSource();
                    if(type.compareTo("ENTRY_CREATE")==0 || type.compareTo("ENTRY_DELETE")==0) {
                        //fileChangeSource.sendMessage(filename,type);
                        amqptest.template.convertAndSend("fileQueue", filename+ " "+type);
                    }
                }
                watchKey.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            System.out.println("fdsfsdf");
            try {
                service.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
