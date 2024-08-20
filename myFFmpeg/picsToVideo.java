package com.example.meitu2.myFFmpeg;

import com.example.meitu2.utils.outerCompressionUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class picsToVideo {
    /*
    想法是造轮子
    将输入的许多份bufferedImages转储成mp4/avi/outputStream
     */
    // 测试用例在videoCompressionController中
    public byte[] p2V(List<BufferedImage> bufferedImages) throws IOException {
        return outerCompressionUtils.photosToCompressedBytes(bufferedImages);
    }
}
