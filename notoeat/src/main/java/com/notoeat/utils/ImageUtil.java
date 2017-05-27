package com.notoeat.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * Created by l on 2017/5/3.
 */
public class ImageUtil {
    private static ImageUtil instance = new ImageUtil();
    private ImageUtil(){
    }
    /**
     * 在指定位置生成缩略图
     */
    public void makeThumbnail(File src, File path,Integer width,Integer height) throws IOException {
        Thumbnails.Builder builder = Thumbnails.of(src);
        if(width!=null){
            builder.width(width);
        }
        if(height!=null){
            builder.height(height);
        }
        builder.toFile(path);
    }

    public static ImageUtil  getInstance() {
        return instance;
    }
}
