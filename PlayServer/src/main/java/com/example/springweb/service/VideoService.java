package com.example.springweb.service;


import com.example.springweb.dao.Video;


import com.example.springweb.mapper.VideoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author 业务逻辑层封装sql语句
 */
@Service
public class VideoService {
//    @Resource
//    private StuMapper stuMapper;

    private final VideoMapper videoMapper;


    public VideoService(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public List<Video> getVideoList() {
        List<Video> list = StreamSupport.stream(videoMapper.findAll().spliterator(),false).collect(Collectors.toList());
        return list;
    }


    public void InsertV(Video v){
        videoMapper.save(v);
    }



    public void UpdateByID(Video v){
        videoMapper.save(v);
    }


    public void DeleteByID(String id){
        videoMapper.deleteById(id);
    }


}