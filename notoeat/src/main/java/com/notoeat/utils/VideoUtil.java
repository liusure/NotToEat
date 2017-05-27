package com.notoeat.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by l on 2017/5/11.
 */
public class VideoUtil {
    private static VideoUtil instance = new VideoUtil();
    private String ffmpegPath;
    private VideoUtil(){};
    public File makeThumbnail(File srcFile,File file) throws IOException {
        ffmpegPath = VideoUtil.class.getResource("ffmpeg.exe").getPath().substring(1);
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(srcFile.getAbsolutePath()); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        commands.add("-y");
        commands.add("-f");
        commands.add("gif");
        commands.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        commands.add("1"); // 添加起始时间为第1秒
        commands.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        commands.add("5"); // 添加持续时间为3秒
        commands.add("-r");
        commands.add("5");
        commands.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        commands.add("160*90"); // 添加截取的图片大小
        commands.add(file.getAbsolutePath()); // 添加截取的图片的保存路径
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(commands);
        builder.start();
        return file;
    }

    public static VideoUtil getInstance() {
        return instance;
    }
}
