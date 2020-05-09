package com.example.springweb.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
public class FileRestController {

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;


    /**
     * 预览视频文件, 支持 byte-range 请求
     */

    @GetMapping("/play")
    public void videoPreview(@RequestParam("re")String re,@RequestParam("name")String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String VideoPath = "I:\\2020spring\\软件体系结构\\hw\\hw5\\bin\\video";
        String path = VideoPath+"\\"+re+"\\"+name;
        System.out.println("play: "+path);
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

}