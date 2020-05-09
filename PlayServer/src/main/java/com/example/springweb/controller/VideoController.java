package com.example.springweb.controller;


import com.example.springweb.dao.Video;
import com.example.springweb.service.*;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 控制器
 */
@RestController
public class VideoController {
    String ImagePath = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\image";
    String VideoPath = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\video";

    @Autowired
    VideoService videoService;

    @GetMapping(value = "/getSize")
    public ResponseEntity<?> getSize()
    {
        List<Video> videos = videoService.getVideoList();
        System.out.println("has: "+videos.size());
        return  ResponseEntity.ok(videos.size());
    }

    @GetMapping(value = "/video/{index}")
    public ResponseEntity<?> getVideo(@PathVariable int index)
    {
        List<Video> videos = videoService.getVideoList();
        if(videos.get(index) != null)
        {
            return ResponseEntity.ok(videos.get(index));
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/picture/{name}")
    public void getPicture(@PathVariable String name,HttpServletResponse Response) throws IOException {
        System.out.println(ImagePath+"\\"+name);
        File img =new File(ImagePath+"\\"+name);
        InputStream in = new FileInputStream(img);
        OutputStream out = Response.getOutputStream();
        out.write(in.readAllBytes());
        out.flush();
        out.close();
        in.close();
    }
    @DeleteMapping(value = "video/{av}")
    public void DeleteVideo(@PathVariable String av)
    {
        videoService.DeleteByID(av);
        File video = new File(VideoPath+"\\raw\\"+av+".mp4");
        if(video.exists()) {
            video.delete();
        }
    }
//    @GetMapping(value = "play/{av}")
//    public void PlayVideo(@PathVariable String av,HttpServletResponse Response) throws IOException {
//        System.out.println("play: "+av+".mp4");
//        File img =new File(VideoPath+"\\raw\\"+av+".mp4");
//        InputStream in = new FileInputStream(img);
//        OutputStream out = Response.getOutputStream();
//        out.write(in.readAllBytes());
//        out.flush();
//        out.close();
//        in.close();
//    }
}
