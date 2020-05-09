package com.example.springweb.dao;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author 视频基本信息结构
 */
@Data
@Entity
@Table(name = "Video")
public class Video{
    public Video() {
        av = "";
        title ="";
        user ="";
        image ="";
        time = "";
        level = 0;
    }

    public Video(String av, String title, String user, String image, String time, int level) {
        this.av = av;
        this.title = title;
        this.user = user;
        this.image = image;
        this.time = time;
        this.level = level;
    }

    @Id private String av;
    //av号
    @Column(name = "title")
    private String title;
    //标题
    @Column(name = "user")
    private String user;
    //user用户,暂不使用
    @Column(name = "image")
    private String image;
    //image类型
    @Column(name = "time")
    private  String time;
    @Column(name = "level")
    private  int level;
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //封面格式


}

