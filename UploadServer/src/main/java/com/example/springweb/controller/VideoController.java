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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 控制器
 */
@RestController
public class VideoController {
    String ImagePath = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\image";
    String VideoPath = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\video\\raw";
    @Autowired
    VideoData videoData;

    @PostMapping(value = "/video")
    public ResponseEntity<?> ReceiveVideo(@RequestParam("file") MultipartFile file) throws IOException {
        String fullname = file.getOriginalFilename();
        String id = fullname.split("\\.",2)[0];
        String filename = fullname.split("\\.",2)[1];
        String title = filename.substring(0,filename.lastIndexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
        String av = videoData.getAV();

        System.out.println("receive: " + id +" -> "+av);
        File dir = new File(VideoPath);
        if(!dir.exists())
        {
            dir.mkdir();
        }
        FileOutputStream out = new FileOutputStream(dir.getPath()+"\\"+ av + suffix);
        InputStream in = file.getInputStream();
        out.write(in.readAllBytes());
        out.flush();
        out.close();
        File jpg = new File(ImagePath+"\\"+id+".jpg");
        File png = new File(ImagePath+"\\"+id+".png");
        File re = new File(dir.getPath()+"\\"+ av + suffix);
        String type = "";
        if(jpg.exists())
        {
            File image = new File(ImagePath+"\\"+av+".jpg");
            if(image.exists()) {
                image.delete();
            }
            jpg.renameTo(image);
            type = "jpg";
        }
        else if(png.exists())
        {
            File image = new File(ImagePath+"\\"+av+".png");
            if(image.exists()) {
                image.delete();
            }
            jpg.renameTo(image);
            type = "png";
        }
        else
        {
            if(re.exists()) {
                VideoProcessing.grabberVideoFramer(av+suffix, av);
                type = "jpg";
            }
            else
            {
                return ResponseEntity.badRequest().build();
            }
        }

            String time = VideoProcessing.getTime(av+suffix);
            //String time = "";
            int level = VideoProcessing.getLevel(av+suffix);
            videoData.Insert(av,title,"",type,time, level);
            return ResponseEntity.ok("");
    }

    @PostMapping(value = "/image")
    public ResponseEntity<?> ReceiveImage(@RequestParam("file") MultipartFile file,@RequestHeader("AV")String id) throws IOException {
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
        System.out.println("receive: "+id);
        File dir = new File(ImagePath);
        if(!dir.exists())
        {
            dir.mkdir();
        }
        FileOutputStream out = new FileOutputStream(dir.getPath()+"\\"+ id +suffix);
        InputStream in = file.getInputStream();
        out.write(in.readAllBytes());
        out.close();
        File re = new File(dir.getPath()+"\\"+ id+suffix);
        if(re.exists())
        {
            return ResponseEntity.ok("");
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "/picture")
    public String test(@RequestParam("name")String name) throws IOException {
        return "av000000.jpg";
    }

}
