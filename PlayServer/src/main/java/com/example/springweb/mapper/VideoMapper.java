package com.example.springweb.mapper;

import com.example.springweb.dao.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoMapper extends CrudRepository<Video, String> { }
