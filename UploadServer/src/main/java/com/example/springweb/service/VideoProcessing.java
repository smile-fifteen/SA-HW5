package com.example.springweb.service;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class VideoProcessing {
    //视频文件路径：
    public static String videoPath = "I:/2020spring/软件体系结构/hw/hw5/bin/video/raw";

    //存放截取视频某一帧的图片
    public static String videoFramesPath = "I:/2020spring/软件体系结构/hw/hw5/bin/image/1";
    /**
     * 将视频文件帧处理并以“jpg”格式进行存储。
     * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
     *
     * @param videoFileName
     */
    public static int getLevel(String videoFileName) throws FrameGrabber.Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
        fFmpegFrameGrabber.start();
        int level = 0;
        int w = fFmpegFrameGrabber.getImageWidth();
        System.out.println("宽度: "+w);
        if(w >= 1920)
        {
            level = 2;
        }
        else if(w >= 1280)
        {
            level = 1;
        }
        fFmpegFrameGrabber.stop();
        fFmpegFrameGrabber.close();
        return level;
    }
    public static String getTime(String videoFileName) throws FrameGrabber.Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
        fFmpegFrameGrabber.start();

        //获取视频总帧数
        int ftp = fFmpegFrameGrabber.getLengthInFrames();
        int total = (int) (ftp/fFmpegFrameGrabber.getFrameRate());
        int min = total / 60;
        int sec = total % 60;
        String time = min + ":"+String.format("%02d",sec);
        fFmpegFrameGrabber.stop();
        fFmpegFrameGrabber.close();
        return time;
    }

    public static String grabberVideoFramer(String videoFileName, String tar){
        //最后获取到的视频的图片的路径
        String videPicture="I:/2020spring/软件体系结构/hw/hw5/bin/image";
        //Frame对象
        Frame frame = null;
        //标识

        String  time = null;
        int flag = 0;
        try {
			 /*
            获取视频文件
            */
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            int total = (int) (ftp/fFmpegFrameGrabber.getFrameRate());
            int min = total / 60;
            int sec = total % 60;
            time = min + ":"+String.format("%02d",sec);
            System.out.println("时长: " + time);

            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
				/*
				对视频的第五帧进行处理
				 */
                if (frame != null && flag==5) {
                    //文件绝对路径+名字
                    String fileName = videPicture+"/"+ tar + ".jpg";

                    //文件储存对象
                    File outPut = new File(fileName);
                    ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);

                    //视频第五帧图的路径
                    String savedUrl =  "I:/2020spring/软件体系结构/hw/hw5/bin/image/5/"+ outPut.getName();
                    videPicture=savedUrl;
                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
        return time;
    }

    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }

}