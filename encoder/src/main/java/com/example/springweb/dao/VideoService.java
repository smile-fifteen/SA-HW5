package com.example.springweb.dao;

import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VideoService {
    private final VideoMapper videoMapper;

    public VideoService(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public int getLevel(String av) throws InterruptedException {
        System.out.println(av);
        while(!videoMapper.findById(av).isPresent())
        {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return videoMapper.findById(av).get().getLevel();
    }
}
