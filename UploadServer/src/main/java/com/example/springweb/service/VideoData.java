package com.example.springweb.service;

import com.example.springweb.dao.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoData {
    @Autowired
    VideoService videoService;

    public String getAV()
    {
        List<Video> videos = videoService.getVideoList();
        int j = 0;
        for(int i = 0; i < videos.size(); i++)
        {
            String tmp = "av"+String.format("%06d", j);
            if(tmp.compareTo(videos.get(i).getAv()) != 0)
            {
                break;
            }
            j++;
        }
        return "av" + String.format("%06d", j);
    }

    public void Insert(String av, String title, String user, String image, String time, int level)
    {
        Video v = new Video(av,title,user,image,time, level);
        videoService.InsertV(v);
    }
}
